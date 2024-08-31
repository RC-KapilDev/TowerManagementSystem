package com.verizon.usermicroservice.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.usermicroservice.model.User;
import com.verizon.usermicroservice.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findByDeletedStatusFalse();
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findByUserIdAndDeletedStatusFalse(userId);
    }

    public User createUser(User user) {
        user.setUsername(generateUsername(user));
        user.setPassword(generatePassword());
        return userRepository.save(user);
    }

    private String generateUsername(User user) {
        return user.getName().toLowerCase().replaceAll(" ", "_") + RandomStringUtils.randomNumeric(4);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public User updateUser(Integer userId, User userDetails) {
        User user = userRepository.findByUserIdAndDeletedStatusFalse(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setSpecialisation(userDetails.getSpecialisation());
        user.setLocation(userDetails.getLocation());
        user.setRole(userDetails.getRole());
        user.setPincode(userDetails.getPincode());
        user.setActiveStatus(userDetails.isActiveStatus());

        return userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        User user = userRepository.findByUserIdAndDeletedStatusFalse(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setDeletedStatus(true);  // Mark user as deleted
        userRepository.save(user);
    }
    
//authenticate 

    public String authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsernameAndDeletedStatusFalse(username);
        if (userOptional.isEmpty()) {
            return "false";
        }

        User user = userOptional.get();
        // Check plain text password
        if (password.equals(user.getPassword())) {
            return "true";
        } else {
            return "false";
        }
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRoleAndDeletedStatusFalse(role);
    }

    public List<User> getUsersByLocation(Integer pincode) {
        return userRepository.findByPincodeAndDeletedStatusFalse(pincode);
    }
}