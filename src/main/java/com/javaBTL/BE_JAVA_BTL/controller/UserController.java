package com.javaBTL.BE_JAVA_BTL.controller;

import com.javaBTL.BE_JAVA_BTL.model.User;
import com.javaBTL.BE_JAVA_BTL.repository.UserRepository;
import com.javaBTL.BE_JAVA_BTL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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

    @DeleteMapping("")
    public ResponseEntity<String> deleteUser(@RequestParam("id") UUID userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully.");
        } catch (IllegalArgumentException e) {
            // Handle the case when the user with the provided ID does not exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @PostMapping("")
    public ResponseEntity<User> updateUser(@RequestParam("id") UUID userId, @RequestBody User updatedUser) {
        try {
            // Find the existing user by ID
            Optional<User> existingUserOptional = userRepository.findById(userId);

            if (existingUserOptional.isPresent()) {
                User existingUser = existingUserOptional.get();

                // Update the existing user with new info
                if(updatedUser.getName() != null) existingUser.setName(updatedUser.getName());
                if(updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
                if(updatedUser.getPassword() != null) existingUser.setPassword(updatedUser.getPassword());
                // You can update other fields as needed

                // Save the updated user back to the repository
                User updatedUserResult = userRepository.save(existingUser);
                return ResponseEntity.ok(updatedUserResult);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

