package com.tower.workorderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.tower.workorderservice.entity.UserDTO;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:5001/api/users";

    public UserDTO getUserById(Long userId) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(String.valueOf(userId))
                .toUriString();
        try {
            return restTemplate.getForObject("http://localhost:5001/api/users"+userId, UserDTO.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Log and handle the exception
            // For example, log the error and return null or throw a custom exception
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkUserExists(Integer integer) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("exists", String.valueOf(integer))
                .toUriString();
        try {
            // Assuming the API returns a boolean value in the response body
            Boolean exists = restTemplate.getForObject(url, Boolean.class);
            return exists != null && exists;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Log and handle the exception
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return false;
        }
    }
}
