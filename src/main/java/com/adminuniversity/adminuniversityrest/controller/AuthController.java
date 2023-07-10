package com.adminuniversity.adminuniversityrest.controller;

import com.adminuniversity.adminuniversityrest.config.jwt.JwtUtils;
import com.adminuniversity.adminuniversityrest.dto.http.request.LoginRequest;
import com.adminuniversity.adminuniversityrest.dto.http.response.LoginResponse;
import com.adminuniversity.adminuniversityrest.entity.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthController {
    private final AuthenticationManager authManager;

    private final JwtUtils jwtUtils;


    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils, UserDetailsService userService) {
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            User user = (User) authentication.getPrincipal();
            String token = jwtUtils.generateAccessToken(user);

            return ResponseEntity.ok(new LoginResponse(true, token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(false, null));
        }
    }
}
