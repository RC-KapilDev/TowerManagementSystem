package com.verizon.equipmentservice.service;

import com.verizon.equipmentservice.entity.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CombinedService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Equipment> fetchEquipmentsForUser(int userId) {
        String workOrderUrl = "http://localhost:8083/api/workorders/user/" + userId;
        
        // Fetch the list of work orders for the user
        List<Map<String, Object>> workOrderList = restTemplate.getForObject(workOrderUrl, List.class);

        List<Equipment> equipmentList = new ArrayList<>();

        if (workOrderList != null) {
            for (Map<String, Object> workOrder : workOrderList) {
                // Check if workOrderId is present and not null
                if (workOrder.get("workorderId") != null) {
                    Integer workOrderId = (Integer) workOrder.get("workorderId");
                   
                    // Ensure workOrderId is not null
                    if (workOrderId != null) {
                        String equipmentUrl = "http://localhost:8083/api/equipments/workorder/" + workOrderId;
                        Equipment[] equipments = restTemplate.getForObject(equipmentUrl, Equipment[].class);

                        if (equipments != null) {
                            equipmentList.addAll(Arrays.asList(equipments));
                        }
                    }
                }
            }
        }

        return equipmentList;
    }
}
