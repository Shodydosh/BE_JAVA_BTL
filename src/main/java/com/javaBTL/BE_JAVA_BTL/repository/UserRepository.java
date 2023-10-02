package com.javaBTL.BE_JAVA_BTL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaBTL.BE_JAVA_BTL.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
