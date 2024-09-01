package com.towermanagement.maintenanceservice.service;



import com.towermanagement.maintenanceservice.model.TowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TowerService {

    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${tower.api.url}")
    private  String towerApiUrl;

    
      public void validateTowerExists(Integer towerId) {
        String url = "http://localhost:8083/api/towers/" + towerId;
        try {
            restTemplate.getForObject(url, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tower not found");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error validating tower");
        }
    }

}
