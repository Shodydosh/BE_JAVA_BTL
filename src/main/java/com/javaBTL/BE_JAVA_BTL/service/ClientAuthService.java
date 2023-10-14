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
public class ClientAuthService {

    // Secret key for signing the JWT (make sure to keep it secure)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    private final long EXPIRATION_TIME = 864_000_000; // Expiration time (e.g., 10 days)

    public String generateJwtToken(User user) {
        // Build the JWT token
        String jwtToken = Jwts.builder()
                .setSubject(user.getEmail()) // You can use email or another unique identifier as the subject
                .claim("email", user.getEmail()) // Add user's email as a custom claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // You can add more claims here as needed
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();

        System.out.println("Generated JWT Token: " + jwtToken); // Print the generated token
        return jwtToken;
    }


    public String validateAndExtractEmailFromJwt(String jwtToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwtToken)
                    .getBody();

            // Perform additional validation checks here if needed
            // Check for expiration, issuer, custom claims, etc.

            // Extract the email from the custom claim
            String email = claims.get("email", String.class);
            System.out.println("✨✨" + email + "✨✨");

            // Check if the email is not null or empty
            if (email != null && !email.isEmpty()) {
                return email;
            } else {
                // Handle the case where the email claim is missing or empty
                return null;
            }
        } catch (Exception e) {
            // Token validation failed
            // Handle the error (e.g., log it or throw an exception)
            return null;
        }
    }

    public String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
