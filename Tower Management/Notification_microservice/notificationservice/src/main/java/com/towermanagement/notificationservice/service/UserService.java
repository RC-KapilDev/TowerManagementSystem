package com.towermanagement.notificationservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.towermanagement.notificationservice.model.UserDTO;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public UserDTO validateUserExists(Integer userId) {
        String userServiceUrl = "http://localhost:5000/api/users/" + userId; // Replace with actual user service URL

        try {
            return restTemplate.getForObject(userServiceUrl, UserDTO.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}

