package com.example.saebackend.controllers;

import com.example.saebackend.services.JWTService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final JWTService jwtService;
    private final JwtDecoder jwtDecoder;

    public LoginController(JWTService jwtService, JwtDecoder jwtDecoder) {
        this.jwtService = jwtService;
        this.jwtDecoder = jwtDecoder;
    }

    @PostMapping("/login")
    public String getToken(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }

    @GetMapping("/user/current")
    public Object getUserInfo(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Jwt decodedJwt = jwtDecoder.decode(token);
        return decodedJwt.getClaims().get("sub");
    }
}
