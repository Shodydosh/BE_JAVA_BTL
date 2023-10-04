package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.User;
import com.javaBTL.BE_JAVA_BTL.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());

        if (existingUser.isPresent()) {
            return userRepository.save(user);
        } else {
            // Handle the case when the user with the provided ID does not exist
            throw new IllegalArgumentException("User with ID " + user.getId() + " does not exist.");
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
