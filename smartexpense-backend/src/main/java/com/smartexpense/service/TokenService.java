package com.smartexpense.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.smartexpense.domain.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateToken(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getSubject();
    }
}
