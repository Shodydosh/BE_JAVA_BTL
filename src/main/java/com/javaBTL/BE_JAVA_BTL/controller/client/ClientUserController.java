package com.javaBTL.BE_JAVA_BTL.controller.client;

import com.javaBTL.BE_JAVA_BTL.model.User;
import com.javaBTL.BE_JAVA_BTL.service.ClientAuthService;
import com.javaBTL.BE_JAVA_BTL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/client/users")
public class ClientUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ClientAuthService clientAuthService;


    @GetMapping("")
    public ResponseEntity<User> getUserById(
            @RequestParam("id") UUID userId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        try {
            String token = clientAuthService.extractTokenFromAuthorizationHeader(authorizationHeader);
            String email = clientAuthService.validateAndExtractEmailFromJwt(token);

            if (email == null) {
                // Token is invalid
                System.out.println("INVALID TOKEN -> " + token);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            User user = userService.getUserById(userId);
            if (user != null) {
                // Check if the retrieved user's email matches the email in the token
                if (email.equals(user.getEmail())) {
                    return ResponseEntity.ok(user);
                } else {
                    System.out.println("EMAIL MISMATCH -> Token email: " + email + ", User email: " + user.getEmail());
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            } else {
                System.out.println("USER NOT FOUND -> ID: " + userId);
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            // Handle the case where the provided "id" parameter is not a valid UUID
            System.out.println("INVALID UUID -> " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestParam("id") UUID userId, @RequestBody User updatedUser) {
        try {
            User updatedUserResult = userService.updateUser(userId, updatedUser);

            if (updatedUserResult != null) {
                return ResponseEntity.ok(updatedUserResult);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

