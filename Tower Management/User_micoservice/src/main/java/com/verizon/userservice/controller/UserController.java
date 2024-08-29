package com.verizon.userservice.controller;

import com.verizon.userservice.model.User;
import com.verizon.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable Long user_id) {
        User user = userService.getUserById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable Long user_id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(user_id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long user_id) {
        userService.deleteUser(user_id);
        return ResponseEntity.noContent().build();
    }
}

