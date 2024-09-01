package com.verizon.usermicroservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.verizon.usermicroservice.model.User;
import com.verizon.usermicroservice.service.UserService;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<User> getUserById(@PathVariable("user_id") Integer userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable("user_id") Integer userId, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_id") Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

//authentication

@PostMapping("/authenticate")
public ResponseEntity<User> authenticateUser(
    @RequestParam String username,
    @RequestParam String password) {

    User result = userService.authenticateUser(username, password);
    return ResponseEntity.ok(result);
}

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable("role") String role) {
        List<User> users = userService.getUsersByRole(role);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/pincode/{location}")
    public ResponseEntity<List<User>> getUsersByLocation(@PathVariable("location") Integer location) {
        List<User> users = userService.getUsersByLocation(location);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }
}
