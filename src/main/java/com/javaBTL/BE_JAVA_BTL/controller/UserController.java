package com.javaBTL.BE_JAVA_BTL.controller;

import com.javaBTL.BE_JAVA_BTL.model.User;
import com.javaBTL.BE_JAVA_BTL.service.AuthService;
import com.javaBTL.BE_JAVA_BTL.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/add")
    public String add(@RequestBody User user){
        userService.saveUser(user);
        return "User added successfully";
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
    @GetMapping("")
    public ResponseEntity<User> getUserById(
            @RequestParam("id") UUID userId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        try {
            // Extract the token part without the "Bearer" prefix
            String token = extractTokenFromAuthorizationHeader(authorizationHeader);

            // Check if the JWT token is valid and extract the email
            String email = authService.validateAndExtractEmailFromJwt(token);

            if (email == null) {
                // Token is invalid
                System.out.println("INVALID TOKEN -> " + token);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // Token is valid, proceed to fetch the user
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

    private String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Remove the "Bearer " prefix to get just the token
            return authorizationHeader.substring(7);
        }
        // If the Authorization header is not in the expected format, return null or handle it accordingly.
        return null;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam("id") UUID userId) {
        try {
            userService.deleteUser(userId);
            String successMessage = "User deleted successfully.";
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } catch (IllegalArgumentException e) {
            // Handle the case when the user with the provided ID does not exist
            String notFoundMessage = "User not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        } catch (Exception e) {
            // Handle other potential exceptions or log them
            String errorMessage = "Error deleting user.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
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

