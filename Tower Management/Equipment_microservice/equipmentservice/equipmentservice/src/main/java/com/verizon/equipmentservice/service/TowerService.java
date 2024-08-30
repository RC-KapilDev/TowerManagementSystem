package com.verizon.equipmentservice.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.verizon.equipmentservice.entity.TowerDTO;

@Service
public class TowerService {

    private final RestTemplate restTemplate;
    private final String towerServiceUrl = "http://localhost:5001/api/towers/";

    public TowerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches a tower by its ID using a REST call to the tower microservice.
     * @param towerId The ID of the tower.
     * @return TowerDTO if found; null otherwise.
     */
    public TowerDTO getTowerIfExists(int towerId) {
        String url = towerServiceUrl + towerId;
        try {
            ResponseEntity<TowerDTO> response = restTemplate.getForEntity(url, TowerDTO.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                return null;
            }
        } catch (HttpClientErrorException.NotFound e) {
            // Tower not found (404 error), return null
            return null;
        } catch (Exception e) {
            // Handle other exceptions (e.g., network issues)
            e.printStackTrace(); // Optionally log the error
            return null;
        }
    }
}
