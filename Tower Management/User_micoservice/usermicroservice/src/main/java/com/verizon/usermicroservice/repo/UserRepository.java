package com.verizon.usermicroservice.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.verizon.usermicroservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByDeletedStatusFalse(); // Find all users not marked as deleted

    Optional<User> findByUserIdAndDeletedStatusFalse(Long userId);  // Find user by ID and ensure not deleted

    Optional<User> findByUsernameAndDeletedStatusFalse(String username);
}
