package com.fanset.dms.user.service.implementation;

import com.fanset.dms.auth.service.AuthenticationService;
import com.fanset.dms.auth.service.JwtService;
import com.fanset.dms.user.Job.JobRecord;
import com.fanset.dms.user.Job.JobRepository;
import com.fanset.dms.user.department.DepartmentRecord;
import com.fanset.dms.user.department.DepartmentRepository;
import com.fanset.dms.user.dto.AuthenticationResponse;
import com.fanset.dms.user.dto.ChangePasswordRequestDto;
import com.fanset.dms.user.dto.UserDto;
import com.fanset.dms.user.enums.Role;
import com.fanset.dms.user.model.User;
import com.fanset.dms.user.projections.UserProjectionInfo;
import com.fanset.dms.user.repository.UserRepository;
import com.fanset.dms.user.service.interfaces.UserServiceInterface;
import com.fanset.dms.user.token.enums.TokenType;
import com.fanset.dms.user.token.model.Token;
import com.fanset.dms.user.token.repository.TokenRepository;
import com.fanset.dms.utils.error_handling.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor // To automatically inject our dependencies
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final EmailValidator emailValidator;
    private final AuthenticationService authenticationService;
    private final DepartmentRepository departmentRepository;
    private final JobRepository jobRepository;

    @Override
    public Page<User> findAllUsers(int pageNumber, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "userId")));
    }


    public List<UserProjectionInfo> findAllUsersInfo() {
        return userRepository.findAllProjection();
    }

    @Override
    public List<User> findUsersByRole(Role role) {
        return userRepository.findUsersByRole(role);
    }




//    public Page<User> findAllTechnicians(int pageNumber,int pageSize) {
//        return userRepository.findAllTechnicians();
//    }

    @Override
    public boolean ifUserExists(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AuthenticationResponse createUser(UserDto userDto) {

        User userFoundByEmail = userRepository.findUserByEmail(userDto.email());
        User userFoundByPhone = userRepository.findUserByPhoneNumber(userDto.phoneNumber());

        if (userFoundByEmail != null){
            throw new DuplicateKeyException("That email already taken");
        }
        if (userFoundByPhone != null){
            throw new DuplicateKeyException("That phone number already taken");
        }
        boolean isValidEmail = emailValidator.test(userDto.email());
        if (!isValidEmail){
            throw new IllegalStateException("Email not valid");
        }

        User user = new User();
        user.setEmail(userDto.email());
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setDateOfBirth(userDto.dateOfBirth());
        user.setPhoneNumber(userDto.phoneNumber());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setRole(userDto.role());

        user.setNationalId(userDto.nationalId());
        user.setNationality(userDto.nationality());
        user.setPassportNumber(userDto.passportNumber());

        user.setAddress(userDto.address().address());
        user.setCity(userDto.address().city());
        user.setState(userDto.address().state());
        user.setCountry(userDto.address().country());

        user.setNextOfKeenAddress(userDto.address().nextOfKeenAddress());
        user.setNextOfKeenCity(userDto.address().nextOfKeenCity());
        user.setNextOfKeenState(userDto.address().nextOfKeenState());
        user.setNextOfKeenCountry(userDto.address().nextOfKeenCountry());
        user.setNextOfKeenPhoneNumber(userDto.address().nextOfKeenPhoneNumber());
        user.setNextOfKeenRelationship(userDto.address().nextOfKeenRelationship());

        DepartmentRecord departmentRecord = departmentRepository.findByName(userDto.department());
        JobRecord jobRecord = jobRepository.findByTitle(userDto.jobTitle());
        user.setDepartment(departmentRecord);
//        user.(jobRecord);


        //contacts information

        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .userId(savedUser.getUserId())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .phoneNumber(savedUser.getPhoneNumber())
//                .user(user)
                .build();
//        return savedUser;
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with the id [ %s ] not found".formatted(userId)));
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

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with that email not found"));
    }

    @Override
    public User updateUser(Long userId, UserDto userDto) {
        System.out.println("userId = " + userId + ", userDto = " + userDto);
        // Check if the current user is an admin or the owner of the account
//            if (!isAuthorized(userId, principal)) {
//                throw   new IllegalStateException("You are not authorized");
//            }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth = " + auth);
        boolean updates = false;
        User foundUser = findUserById(userId);

        if(!foundUser.getEmail().equals(userDto.email()) && userDto.email() != null){
            foundUser.setEmail(userDto.email());
            updates=true;
        }

        if(!foundUser.getFirstName().equals(userDto.firstName()) && userDto.firstName() != null){
            foundUser.setFirstName(userDto.firstName());
            updates=true;
        }
        if(!foundUser.getLastName().equals(userDto.lastName()) && userDto.lastName() != null){
            foundUser.setLastName(userDto.lastName());
            updates=true;
        }

        if(!foundUser.getDateOfBirth().equals(userDto.dateOfBirth()) && userDto.dateOfBirth() != null){
            foundUser.setDateOfBirth(userDto.dateOfBirth());
            updates=true;
        }

        if(!foundUser.getPhoneNumber().equals(userDto.phoneNumber()) && userDto.phoneNumber() != null){
            foundUser.setPhoneNumber(userDto.phoneNumber());
            updates=true;
        }



        if(!foundUser.getRole().equals(userDto.role()) && userDto.role() != null){
            foundUser.setRole(userDto.role());
            updates=true;
        }

        if(!foundUser.getEmail().equals(userDto.email()) && userDto.email() != null){
            foundUser.setEmail(userDto.email());
            updates=true;
        }
        if (updates){

        return userRepository.save(foundUser);
        }

            return foundUser;
    }

    @Override
    public String deleteUserById(Long userId) {
        if (ifUserExists(userId)){
            userRepository.deleteById(userId);

            return "User with id [%s] deleted successfully".formatted(userId);
        }else {
            throw  new ResourceNotFoundException("User with userId [%s] not found".formatted(userId));
        }

    }

    @Override
    public AuthenticationResponse findUserProfile(String token) {

        User foundUser = userRepository.findUserByToken(token).orElseThrow(() -> new ResourceNotFoundException("User with token not found"));
        var jwtToken = jwtService.generateToken(foundUser);
        var refreshToken = jwtService.generateRefreshToken(foundUser);
        authenticationService.revokeAllUserTokens(foundUser);
        saveUserToken(foundUser, jwtToken);
         return AuthenticationResponse.builder()
                .userId(foundUser.getUserId())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .firstName(foundUser.getFirstName())
                .lastName(foundUser.getLastName())
                .username(foundUser.getUsername())
                .email(foundUser.getEmail())
                .role(foundUser.getRole())
                .phoneNumber(foundUser.getPhoneNumber())
//                .user(user)
                .build();
    }

    @Override
    public Long getUserId(String username) {
        User user = userRepository.findUserByEmail(username);

        System.out.println("################################################################");
        System.out.println(user.getUserId());
        System.out.println("################################################################");

        return user.getUserId();
    }

    @Override
    public User changePassword(ChangePasswordRequestDto changePasswordRequestDto, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
//        Check is the current password is correct
        if (!passwordEncoder.matches(changePasswordRequestDto.getCurrentPassword(), user.getPassword())){
            throw new  IllegalStateException("Wrong password");
        }
//        Check if the new Password is not equal to the old Password
        if (passwordEncoder.matches(changePasswordRequestDto.getNewPassword(), user.getPassword())){
            throw new  IllegalStateException("New Password cant be the same as Old password");
        }
//        Check is the two new password are the same
        if (!changePasswordRequestDto.getNewPassword().equals(changePasswordRequestDto.getConfirmationPassword())){
            throw new  IllegalStateException("Passwords are Not matching");

        }
//       Updating the user password
        user.setPassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));
     return   userRepository.save(user);
    }


    public boolean isAuthorized(Long userId, Principal principal) {

        var user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        // Retrieve the currently authenticated user's ID or username
        Long loggedInUserId = user.getUserId();
        Role role = user.getRole();

        // Check if the user is an admin or the owner of the account
        if (role.equals(Role.ADMIN) || loggedInUserId.equals(userId)) {
            return true;
        }

        return false;
    }
}
