package com.javaBTL.BE_JAVA_BTL.controller;

import com.javaBTL.BE_JAVA_BTL.model.User;
import com.javaBTL.BE_JAVA_BTL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user){
        userService.saveUser(user);
        return "User added successfully";
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            User user = userService.getUserById(uuid);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            // Handle the case where the userId is not a valid UUID
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("")
    public ResponseEntity<User> getUserById(@RequestParam("id") UUID userId) {
        // Your code to retrieve the user by userId
        User user = userService.getUserById(userId);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            // Handle the case where the user is not found
            return ResponseEntity.notFound().build();
        }
    }

}
