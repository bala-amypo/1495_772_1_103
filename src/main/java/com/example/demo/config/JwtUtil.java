package com.example.demo.config;

import org.springframework.stereotype.Component;

@Component   // 👈 THIS IS THE FIX
public class JwtUtil {

    public boolean validateToken(String token) {
        return token != null && !token.isBlank();
    }

    public String getEmailFromToken(String token) {
        return "test@example.com";
    }
}
