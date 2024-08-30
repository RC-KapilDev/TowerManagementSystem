package com.tower.workorderservice.controller;


import com.tower.workorderservice.entity.WorkOrder;
import com.tower.workorderservice.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workorders")
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
        return workOrder.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public WorkOrder createWorkOrder(@RequestBody WorkOrder workOrder) {
        return workOrderService.createWorkOrder(workOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkOrder> updateWorkOrder(@PathVariable Integer id, @RequestBody WorkOrder workOrderDetails) {
        WorkOrder updatedWorkOrder = workOrderService.updateWorkOrder(id, workOrderDetails);
        return ResponseEntity.ok(updatedWorkOrder);
    }

    @GetMapping("/status/{status}")
    public List<WorkOrder> getWorkOrdersByStatus(@PathVariable String status) {
        return workOrderService.getWorkOrdersByStatus(status);
    }

    @GetMapping("/user/{userId}")
    public List<WorkOrder> getWorkOrdersByUserId(@PathVariable Integer userId) {
        return workOrderService.getWorkOrdersByUserId(userId);
    }
}
