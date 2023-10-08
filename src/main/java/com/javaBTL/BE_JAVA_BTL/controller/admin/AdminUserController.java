package com.javaBTL.BE_JAVA_BTL.controller.admin;

import com.javaBTL.BE_JAVA_BTL.model.User;
import com.javaBTL.BE_JAVA_BTL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {
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

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam("id") UUID userId) {
        // Implement code to delete a user accessible to administrators
        return null;
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestParam("id") UUID userId, @RequestBody User updatedUser) {
        // Implement code to update a user accessible to administrators
        return null;
    }
}
