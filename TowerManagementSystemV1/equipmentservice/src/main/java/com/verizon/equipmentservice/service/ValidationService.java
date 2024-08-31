package com.verizon.equipmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.verizon.equipmentservice.entity.TowerDTO;
import com.verizon.equipmentservice.entity.WorkOrderDTO;


@Service
public class ValidationService {
    
    @Autowired
    private RestTemplate restTemplate;


    


    public void validateTowerExists(Integer towerId) {
        String url = "http://localhost:8083/api/towers/" + towerId;
        try {
            restTemplate.getForObject(url, TowerDTO.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tower not found");
        }
    }

    public void validateWorkOrderExists(Integer workorderId) {
        String url = "http://localhost:8083/api/workorders/" + workorderId;
        try {
            restTemplate.getForObject(url, WorkOrderDTO.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Work order not found");
        }
    }
}
