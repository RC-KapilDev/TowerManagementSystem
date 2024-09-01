package com.towermanagement.maintenanceservice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;



@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
      
     @Value("${user.api.url}")
    private String userApiUrl ;

        public void validateUserExists(Integer userId) {
        String url = "http://localhost:8083/api/users/" + userId;
        try {
            restTemplate.getForObject(url, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error validating user");
        }
    }

    
}
