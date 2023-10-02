package com.javaBTL.BE_JAVA_BTL.service;
import com.javaBTL.BE_JAVA_BTL.model.User;
import java.util.List;
public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUser();
}
