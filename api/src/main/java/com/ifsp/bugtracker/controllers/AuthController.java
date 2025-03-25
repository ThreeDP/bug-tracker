package com.ifsp.bugtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.bugtracker.config.auth.TokenProvider;
import com.ifsp.bugtracker.controllers.dtos.AuthTokenResponseDTO;
import com.ifsp.bugtracker.controllers.dtos.AuthUserRequestDTO;
import com.ifsp.bugtracker.controllers.dtos.CreateUserRequestDTO;
import com.ifsp.bugtracker.data.entities.User;
import com.ifsp.bugtracker.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final AuthService authService;
    private final TokenProvider tokenProvider;

    @Autowired
    public AuthController(
        AuthenticationManager authManager,
        AuthService authService,
        TokenProvider tokenProvider
    ) {
        this.authManager = authManager;
        this.authService = authService;
        this.tokenProvider = tokenProvider;
    }


    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody @Valid CreateUserRequestDTO data) {
        authService.signUp(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthTokenResponseDTO> signIn(@RequestBody @Valid AuthUserRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authUser = authManager.authenticate(usernamePassword);
        var accessToken = tokenProvider.generateAccessToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new AuthTokenResponseDTO(accessToken));
    }
}
