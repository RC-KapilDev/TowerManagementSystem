package com.tower.workorderservice.controller;


import com.tower.workorderservice.entity.WorkOrder;
import com.tower.workorderservice.service.WorkOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workorders")
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;

    @GetMapping
    public List<WorkOrder> getAllWorkOrders() {
        return workOrderService.getAllWorkOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkOrder> getWorkOrderById(@PathVariable Integer id) {
        Optional<WorkOrder> workOrder = workOrderService.getWorkOrderById(id);
        return workOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
public ResponseEntity<WorkOrder> createWorkOrder(@RequestBody WorkOrder workOrder) {
    try {
        WorkOrder createdWorkOrder = workOrderService.createWorkOrder(workOrder);
        return ResponseEntity.status(200).body(createdWorkOrder);
    } catch (IllegalArgumentException e) {
        // Handle validation exceptions
        return ResponseEntity.badRequest().body(null);
    }
}

    @PutMapping("/{id}")
    public ResponseEntity<WorkOrder> updateWorkOrder(@PathVariable Integer id, @RequestBody WorkOrder workOrderDetails) {
        try {
            WorkOrder updatedWorkOrder = workOrderService.updateWorkOrder(id, workOrderDetails);
            return ResponseEntity.ok(updatedWorkOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/status/{status}")
    public List<WorkOrder> getWorkOrdersByStatus(@PathVariable String status) {
        return workOrderService.getWorkOrdersByStatus(status);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkOrder>> getWorkOrdersByUserId(@PathVariable Integer userId) {
        try {
            List<WorkOrder> workOrders = workOrderService.getWorkOrdersByUserId(userId);
            return ResponseEntity.ok(workOrders);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
