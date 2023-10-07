package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class AuthService {

    // Secret key for signing the JWT (make sure to keep it secure)
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long EXPIRATION_TIME = 864_000_000; // Expiration time (e.g., 10 days)

    public String generateJwtToken(User user) {
        // Build the JWT token
        String jwtToken = Jwts.builder()
                .setSubject(user.getEmail()) // You can use email or another unique identifier as the subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // You can add more claims here as needed
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();

        return jwtToken;
    }

    public Claims validateAndDecodeJwt(String jwtToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwtToken)
                    .getBody();

            // Perform additional validation checks here if needed
            // Check for expiration, issuer, custom claims, etc.

            return claims;
        } catch (Exception e) {
            // Token validation failed
            // Handle the error (e.g., log it or throw an exception)
            return null;
        }
    }
}
