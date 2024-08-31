package com.towermanagement.workorderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.towermanagement.workorderservice.model.DTO.TowerDTO;
import com.towermanagement.workorderservice.model.DTO.UserDTO;

import org.springframework.http.HttpStatus;

@Service
public class ValidationService {

    @Autowired
    private RestTemplate restTemplate;

    public UserDTO validateUserExists(Integer userId) {
        String url = "http://localhost:8083/api/users/" + userId;
        try {
            return restTemplate.getForObject(url, UserDTO.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public TowerDTO validateTowerExists(Integer towerId) {
        String url = "http://localhost:8083/api/towers/" + towerId;
        try {
            return restTemplate.getForObject(url, TowerDTO.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tower not found");
        }
    }
}

