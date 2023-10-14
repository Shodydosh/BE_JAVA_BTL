package com.javaBTL.BE_JAVA_BTL.controller;

import com.javaBTL.BE_JAVA_BTL.model.User;
import com.javaBTL.BE_JAVA_BTL.service.ClientAuthService;
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
    private ClientAuthService clientAuthService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User " + savedUser.getId() + " added successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User storedUser = userService.getUserByEmail(user.getEmail());

        if (storedUser == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (user.getPassword().equals(storedUser.getPassword())) {
            String jwtToken = clientAuthService.generateJwtToken(storedUser);
            return ResponseEntity.ok(jwtToken);
        } else {
            return ResponseEntity.badRequest().body("Incorrect password");
        }
    }
}
