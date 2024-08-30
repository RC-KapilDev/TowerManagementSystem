package com.towermanagement.workorderservice.service;


import com.towermanagement.workorderservice.model.WorkOrder;
import com.towermanagement.workorderservice.model.DTO.TowerDTO;
import com.towermanagement.workorderservice.model.DTO.UserDTO;
import com.towermanagement.workorderservice.repository.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private ValidationService validationService;

    public List<WorkOrder> getAllWorkOrders() {
        return workOrderRepository.findByDeletedStatusFalse();
    }

    public WorkOrder createWorkOrder(WorkOrder workOrder) {
        // Validate if the tower and user exist
        validationService.validateUserExists(workOrder.getUser());
        validationService.validateTowerExists(workOrder.getTower());

        return workOrderRepository.save(workOrder);
    }

    public Optional<WorkOrder> getWorkOrderById(Integer id) {
        return workOrderRepository.findById(id)
                .filter(workOrder -> !workOrder.getDeletedStatus());
    }

    public WorkOrder updateWorkOrder(Integer id, WorkOrder workOrderDetails) {
        WorkOrder workOrder = getWorkOrderById(id)
                .orElseThrow(() -> new RuntimeException("Work Order not found"));

        // Validate if the tower and user exist
        validationService.validateUserExists(workOrderDetails.getUser());
         validationService.validateTowerExists(workOrderDetails.getTower());

        workOrder.setTower(workOrderDetails.getTower());
        workOrder.setUser(workOrderDetails.getUser());
        workOrder.setTaskDescription(workOrderDetails.getTaskDescription());
        workOrder.setScheduledDate(workOrderDetails.getScheduledDate());
        workOrder.setStatus(workOrderDetails.getStatus());
        workOrder.setCompletedDate(workOrderDetails.getCompletedDate());
        workOrder.setUpdatedAt(LocalDateTime.now());

        return workOrderRepository.save(workOrder);
    }

    public void softDeleteWorkOrder(Integer id) {
        WorkOrder workOrder = getWorkOrderById(id)
                .orElseThrow(() -> new RuntimeException("Work Order not found"));

        workOrder.setDeletedStatus(true);
        workOrder.setUpdatedAt(LocalDateTime.now());
        workOrderRepository.save(workOrder);
    }

    public WorkOrder updateWorkOrderStatus(Integer id, String status) {
        WorkOrder workOrder = getWorkOrderById(id)
                .orElseThrow(() -> new RuntimeException("Work Order not found"));

        workOrder.setStatus(status);
        workOrder.setUpdatedAt(LocalDateTime.now());
        return workOrderRepository.save(workOrder);
    }

    public List<WorkOrder> getWorkOrdersByStatus(String status) {
        return workOrderRepository.findByStatusAndDeletedStatusFalse(status);
    }

    public List<WorkOrder> getWorkOrdersByUserId(Integer userId) {
        return workOrderRepository.findByUserAndDeletedStatusFalse(userId);
    }


    public WorkOrder updateCompletedDate(Integer workOrderId, LocalDateTime completedDate) {
        WorkOrder workOrder = workOrderRepository.findById(workOrderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Work order not found"));

        workOrder.setCompletedDate(completedDate);
        workOrder.setStatus("COMPLETED"); // Update status to COMPLETED
        workOrder.setUpdatedAt(LocalDateTime.now()); // Optionally update the timestamp

        return workOrderRepository.save(workOrder);
    }
}
