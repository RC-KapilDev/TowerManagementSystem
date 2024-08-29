package com.verizon.userservice.service;

import com.verizon.userservice.model.User;
import com.verizon.userservice.repo.UserRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long user_id) {
        return userRepository.findById(user_id);
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

    public User updateUser(Long user_id, User userDetails) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmail(userDetails.getEmail());
        user.setSpecialization(userDetails.getSpecialization());
        user.setLocation(userDetails.getLocation());
        user.setPincode(userDetails.getPincode());
        user.setRole(userDetails.getRole());
        user.setActiveStatus(userDetails.getActiveStatus());
        user.setDeletedStatus(userDetails.getDeletedStatus());
        user.setName(userDetails.getName());

        return userRepository.save(user);
    }

    public void deleteUser(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
