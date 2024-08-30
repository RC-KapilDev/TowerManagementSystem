package com.tower.workorderservice.service;

import com.tower.workorderservice.entity.WorkOrder;
import com.tower.workorderservice.repository.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private UserService userService;  // Inject UserService

    @Autowired
    private TowerService towerService; // Inject TowerService

    public List<WorkOrder> getAllWorkOrders() {
        return workOrderRepository.findAll();
    }

    public Optional<WorkOrder> getWorkOrderById(Integer id) {
        return workOrderRepository.findById(id);
    }

    public WorkOrder createWorkOrder(WorkOrder workOrder) {
        // Check if the user exists before creating the work order
        if (userService.checkUserExists(workOrder.getUserId()) && towerService.getTowerById(workOrder.getTowerId()) != null) {
            return workOrderRepository.save(workOrder);
        } else {
            throw new IllegalArgumentException("User or Tower does not exist");
        }
    }

    public WorkOrder updateWorkOrder(Integer id, WorkOrder workOrderDetails) {
        // Check if the user exists before updating the work order
        if (userService.checkUserExists(workOrderDetails.getUserId()) && towerService.getTowerById(workOrderDetails.getTowerId()) != null) {
            WorkOrder workOrder = workOrderRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("WorkOrder not found with id " + id));
            workOrder.setTaskDescription(workOrderDetails.getTaskDescription());
            workOrder.setStatus(workOrderDetails.getStatus());
            workOrder.setCompletedDate(workOrderDetails.getCompletedDate());
            return workOrderRepository.save(workOrder);
        } else {
            throw new IllegalArgumentException("User or Tower does not exist");
        }
    }

    public List<WorkOrder> getWorkOrdersByStatus(String status) {
        return workOrderRepository.findByStatus(status);
    }

    public List<WorkOrder> getWorkOrdersByUserId(Integer userId) {
        // Check if the user exists before fetching work orders
        if (userService.checkUserExists(userId)) {
            return workOrderRepository.findByUserId(userId);
        } else {
            throw new IllegalArgumentException("User does not exist");
        }
    }
}
