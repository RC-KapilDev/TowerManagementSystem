package com.towermanagement.maintenanceservice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.towermanagement.maintenanceservice.model.UserDTO;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
      
     @Value("${user.api.url}")
    private String userApiUrl ;

    public UserDTO  validateUserExists(Integer userId) {
        try{

            return restTemplate.getForObject(userApiUrl + userId, UserDTO.class);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
    }

    
}
