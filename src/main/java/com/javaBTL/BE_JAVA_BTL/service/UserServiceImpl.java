package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.User;
import com.javaBTL.BE_JAVA_BTL.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(UUID userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
    }

    @Override
    public User updateUser(UUID userId, User updatedUser) {
        try {
            // Find the existing user by ID
            Optional<User> existingUserOptional = userRepository.findById(userId);

            if (existingUserOptional.isPresent()) {
                User existingUser = existingUserOptional.get();

                // Update the existing user with new info
                if (updatedUser.getName() != null) {
                    existingUser.setName(updatedUser.getName());
                }
                if (updatedUser.getEmail() != null) {
                    existingUser.setEmail(updatedUser.getEmail());
                }
                if (updatedUser.getPassword() != null) {
                    existingUser.setPassword(updatedUser.getPassword());
                }
                // You can update other fields as needed

                // Save the updated user back to the repository
                User updatedUserResult = userRepository.save(existingUser);

                // Return the updated user
                return updatedUserResult;
            } else {
                // Throw an exception when the user with the provided ID does not exist
                throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
            }
        } catch (Exception e) {
            // Handle other potential exceptions or log them
            throw new RuntimeException("Error updating user with ID " + userId, e);
        }
    }

    @Override
    public void deleteUser(UUID userId) {
        Optional<User> existingUser = userRepository.findById(userId);

        if (existingUser.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            // Handle the case when the user with the provided ID does not exist
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }
    }
}
