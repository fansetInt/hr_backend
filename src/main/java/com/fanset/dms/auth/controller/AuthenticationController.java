package com.fanset.dms.auth.controller;

import com.fanset.dms.auth.service.AuthenticationService;
import com.fanset.dms.user.dto.AuthenticationRequest;
import com.fanset.dms.user.dto.AuthenticationResponse;
import com.fanset.dms.user.dto.RegisterRequest;
import com.fanset.dms.user.service.implementation.UserServiceImpl;
import com.fanset.dms.utils.response_handler.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "AUTHENTICATION ALL USERS")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService service;
    private  final UserServiceImpl userService;
    @Operation(
            description = "Any User create an account using this endpoint including ADMIN",
            summary = "REGISTER NEW USER",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorised / TokenInvalid",
                            responseCode = "403"
                    ),
            }


    )
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        try {
            AuthenticationResponse savedUser = service.register(request);
            return ResponseHandler.generateResponse(
                    "User Created Successfully", HttpStatus.CREATED, savedUser,1, true
            );
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.CONFLICT, null,0, false);
        }
    }

    @Operation(
            description = "Users Login endpoint",
            summary = "USERS LOGIN",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorised / TokenInvalid",
                            responseCode = "403"
                    ),
            }


    )
    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request) {
    try {
        AuthenticationResponse foundUser = service.authenticate(request);
        return ResponseHandler.generateResponse(
            "User Successfully Authenticated", HttpStatus.OK, foundUser,1, true
    );
    }catch (Exception e){
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null,0, false);
}
    }

    @Operation(
            description = "Users Refresh Token endpoint",
            summary = "USERS REFRESH TOKEN",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorised / TokenInvalid",
                            responseCode = "403"
                    ),
            }


    )
//    @Hidden
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}