package com.ifsp.bugtracker.config.auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ifsp.bugtracker.data.entities.User;

@Service
public class TokenProvider {
    @Value("${security.jwt.token.secret-key}")
    private String jwtSecret;

    @Value("${security.jwt.token.expiry.hour}")
    private int expiryHour;

    @Value("${security.jwt.token.expiry.zone}")
    private String expiryZone;

    public String generateAccessToken(User user) {
        try {
            Algorithm alg = Algorithm.HMAC256(jwtSecret);
            return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("username", user.getUsername())
                .withExpiresAt(genAccessExpirationDate())
                .sign(alg);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating token", exception);
        }
    }

    public String validationToken(String token) {
        try {
            Algorithm alg = Algorithm.HMAC256(jwtSecret);
            return JWT.require(alg)
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating token", exception);
        }
    }

    private Instant genAccessExpirationDate() {
        return LocalDateTime.now().plusHours(expiryHour).toInstant(ZoneOffset.of("-03:00"));
    }
}
