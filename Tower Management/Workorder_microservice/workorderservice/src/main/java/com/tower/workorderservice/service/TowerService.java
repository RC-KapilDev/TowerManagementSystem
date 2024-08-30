package com.tower.workorderservice.service;



import com.tower.workorderservice.entity.TowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TowerService {

    @Autowired
    private final RestTemplate restTemplate;

    public TowerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String BASE_URL = "http://localhost:5000/api/towers";

    public TowerDTO getTowerById(int towerId) {
        try {
            String url = String.format("%s/%d", BASE_URL, towerId);
            ResponseEntity<TowerDTO> response = restTemplate.getForEntity(url, TowerDTO.class);
            return response.getStatusCode().is2xxSuccessful() ? response.getBody() : null;
        } catch (Exception e) {
            // Handle exceptions, e.g., logging
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkTowerExists(int towerId) {
        try {
            String url = String.format("%s/exists/%d", BASE_URL, towerId);
            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
            return response.getStatusCode().is2xxSuccessful() && Boolean.TRUE.equals(response.getBody());
        } catch (Exception e) {
            // Handle exceptions, e.g., logging
            e.printStackTrace();
            return false;
        }
    }
}

