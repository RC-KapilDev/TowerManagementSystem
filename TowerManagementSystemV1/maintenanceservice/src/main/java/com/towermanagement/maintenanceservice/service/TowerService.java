package com.towermanagement.maintenanceservice.service;



import com.towermanagement.maintenanceservice.model.TowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TowerService {

    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${tower.api.url}")
    private  String towerApiUrl;

    
    public TowerDTO validateTowerExists(Integer towerId) {
        try {
            // Attempt to fetch the tower by ID
            return restTemplate.getForObject(towerApiUrl + towerId, TowerDTO.class);
        } catch (Exception e) {
            // If an exception occurs (e.g., 404 Not Found), throw a ResponseStatusException
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tower not found", e);
        }
    }
}
