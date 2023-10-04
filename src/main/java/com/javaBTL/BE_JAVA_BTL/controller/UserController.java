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
            System.out.println("USER NOT FOUND..");
            return ResponseEntity.notFound().build();
        }
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

