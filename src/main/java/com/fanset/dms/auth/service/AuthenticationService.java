package com.fanset.dms.auth.service;

import com.fanset.dms.user.dto.AuthenticationRequest;
import com.fanset.dms.user.dto.AuthenticationResponse;
import com.fanset.dms.user.dto.RegisterRequest;
import com.fanset.dms.user.model.User;
import com.fanset.dms.user.repository.UserRepository;
import com.fanset.dms.user.service.implementation.EmailValidator;
import com.fanset.dms.user.token.enums.TokenType;
import com.fanset.dms.user.token.model.Token;
import com.fanset.dms.user.token.repository.TokenRepository;
import com.fanset.dms.utils.error_handling.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailValidator emailValidator;

    public AuthenticationResponse register(RegisterRequest request) {
        User userFoundByEmail = userRepository.findUserByEmail(request.getEmail());
        User userFoundByPhone = userRepository.findUserByPhoneNumber(request.getPhoneNumber());
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("Email not valid");
        }
        if (userFoundByEmail != null){
            throw new DuplicateKeyException("That email already taken");
        }
        if (userFoundByPhone != null){
            throw new DuplicateKeyException("That phone number already taken");
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .phoneNumber(request.getPhoneNumber())

                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())

                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .userId(user.getUserId())
                .role(user.getRole())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow( () -> new ResourceNotFoundException("User with that email not found"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        //todo:: to return token revocation here
//        revokeAllUserTokens(user);
        //delete token
        deleteAllUserTokens(user);

        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .userId(user.getUserId())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())

                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
//                .user(user)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }


    public void deleteAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        // Delete the valid tokens instead of revoking them
        tokenRepository.deleteAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
