package com.javaBTL.BE_JAVA_BTL.controller;

import com.javaBTL.BE_JAVA_BTL.repository.UserRepository;
import com.javaBTL.BE_JAVA_BTL.model.User;
import com.javaBTL.BE_JAVA_BTL.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User " + savedUser.getId() + " added successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User storedUser = userRepository.findByEmail(user.getEmail());

        if (storedUser == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (user.getPassword().equals(storedUser.getPassword())) {
            String jwtToken = generateJwtToken(storedUser);
            return ResponseEntity.ok("Login successful. Token: " + jwtToken);
        } else {
            return ResponseEntity.badRequest().body("Incorrect password");
        }
    }

    private String generateJwtToken(User user) {
        // Implement JWT token generation logic here
        // You can use libraries like Spring Security or jjwt for this purpose
        // Return the generated token as a string
        return "your_generated_jwt_token_here";
    }
}
