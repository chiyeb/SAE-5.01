package com.example.saebackend.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.example.saebackend.domain.users.UserModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

/**
 * Service class for handling JWT operations.
 * Provides methods for generating and decoding JWT tokens.
 */
@Service
public class JWTService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public JWTService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    /**
     * Generates a JWT token for the authenticated user.
     *
     * @param authentication The authentication object containing user details.
     * @return The generated JWT token as a string.
     */
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        UserModel userInfo = (UserModel) authentication.getPrincipal();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(userInfo.getId().toString())
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

    /**
     * Retrieves the user ID from the given JWT token.
     *
     * @param token The JWT token.
     * @return The user ID extracted from the token.
     */
    public String getLoggedUserId(String token) {
        return jwtDecoder.decode(token).getSubject();
    }
}
