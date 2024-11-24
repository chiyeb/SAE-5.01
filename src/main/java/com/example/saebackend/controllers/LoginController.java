package com.example.saebackend.controllers;

import com.example.saebackend.domain.users.UserModel;
import com.example.saebackend.services.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling login requests.
 * Provides an endpoint for generating JWT tokens upon successful authentication.
 */
@RestController
public class LoginController {
    private final JWTService jwtService;

    public LoginController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String getToken(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }

    @PostMapping("/login/intranet")
    public ResponseEntity<String> getTokenIntranet(Authentication authentication) {
        UserModel userModel = (UserModel) authentication.getPrincipal();
        if (userModel.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return ResponseEntity.ok(jwtService.generateToken(authentication));
        }
        return ResponseEntity.status(401).build();
    }
}
