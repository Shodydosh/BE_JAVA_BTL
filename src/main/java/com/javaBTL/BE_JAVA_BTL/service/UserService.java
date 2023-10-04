package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();
    User getUserById(UUID userId);

    User updateUser(UUID userId, User user);

    void deleteUser(UUID userId);
}
