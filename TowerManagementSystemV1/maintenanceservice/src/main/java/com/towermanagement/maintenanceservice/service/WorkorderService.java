package com.towermanagement.maintenanceservice.service;


import com.towermanagement.maintenanceservice.model.WorkorderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WorkorderService {

    @Autowired
    private RestTemplate restTemplate;

    private final String workOrderApiUrl = "http://localhost:8083/api/workorders/";

    public void validateWorkOrderExists(Integer workOrderId) {
        try {
            restTemplate.getForObject(workOrderApiUrl + workOrderId, WorkorderDTO.class);
           
           
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Work Order not found");
        }
    }
}
