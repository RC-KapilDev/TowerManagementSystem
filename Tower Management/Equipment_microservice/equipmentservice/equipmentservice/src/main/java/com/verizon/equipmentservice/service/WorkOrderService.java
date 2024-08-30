package com.verizon.equipmentservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.verizon.equipmentservice.entity.WorkOrderDTO;

import org.springframework.http.ResponseEntity;
import java.util.List;

@Service
public class WorkOrderService {

    private final RestTemplate restTemplate;
    private final String workorderServiceUrl = "http://localhost:5000/api/workorders/";

    public WorkOrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches a work order by its ID.
     * @param workorderId The ID of the work order.
     * @return WorkorderDTO if found; null otherwise.
     */
    public WorkOrderDTO getWorkorderById(int workorderId) {
        String url = workorderServiceUrl + workorderId;
        try {
            ResponseEntity<WorkOrderDTO> response = restTemplate.getForEntity(url, WorkOrderDTO.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Fetches a list of work orders by status.
     * @param status The status of the work orders.
     * @return List of WorkorderDTO objects.
     */
    public List<WorkOrderDTO> getWorkordersByStatus(String status) {
        String url = "http://localhost:5000/api/workorders?status=" + status;
        try {
            WorkOrderDTO[] workorderArray = restTemplate.getForObject(url, WorkOrderDTO[].class);
            return List.of(workorderArray); // Convert array to list
        } catch (Exception e) {
            return List.of(); // Return empty list if there's an error
        }
    }
}
