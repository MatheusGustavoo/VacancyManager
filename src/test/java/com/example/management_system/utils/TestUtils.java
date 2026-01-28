package com.example.management_system.utils;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
    @Value("${security.token.secret}")
    private String key;

    public static String asJsonString(Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    public static String generateToken(String primaryKey, String secret){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
            .withSubject(primaryKey)
            .withExpiresAt(expiresIn)
            .sign(algorithm);
        return token;
    }
}
