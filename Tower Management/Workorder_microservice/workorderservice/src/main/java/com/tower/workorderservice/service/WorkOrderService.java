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

    public List<WorkOrder> getAllWorkOrders() {
        return workOrderRepository.findAll();
    }

    public Optional<WorkOrder> getWorkOrderById(Integer id) {
        return workOrderRepository.findById(id);
    }

    public WorkOrder createWorkOrder(WorkOrder workOrder) {
        return workOrderRepository.save(workOrder);
    }

    public WorkOrder updateWorkOrder(Integer id, WorkOrder workOrderDetails) {
        WorkOrder workOrder = workOrderRepository.findById(id).orElseThrow();
        workOrder.setTaskDescription(workOrderDetails.getTaskDescription());
        workOrder.setStatus(workOrderDetails.getStatus());
        workOrder.setCompletedDate(workOrderDetails.getCompletedDate());
        return workOrderRepository.save(workOrder);
    }

    public List<WorkOrder> getWorkOrdersByStatus(String status) {
        return workOrderRepository.findByStatus(status);
    }

    public List<WorkOrder> getWorkOrdersByUserId(Integer userId) {
        return workOrderRepository.findByUserId(userId);
    }
}
