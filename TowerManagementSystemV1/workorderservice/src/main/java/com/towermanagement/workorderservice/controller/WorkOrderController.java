package com.towermanagement.workorderservice.controller;


import com.towermanagement.workorderservice.model.WorkOrder;
import com.towermanagement.workorderservice.service.WorkOrderService;
import com.towermanagement.workorderservice.utils.CompletedDateUpdateRequest;

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

    @PostMapping
    public WorkOrder createWorkOrder(@RequestBody WorkOrder workOrder) {
        return workOrderService.createWorkOrder(workOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkOrder> getWorkOrderById(@PathVariable Integer id) {
        Optional<WorkOrder> workOrder = workOrderService.getWorkOrderById(id);
        return workOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkOrder> updateWorkOrder(@PathVariable Integer id, @RequestBody WorkOrder workOrderDetails) {
        try {
            WorkOrder updatedWorkOrder = workOrderService.updateWorkOrder(id, workOrderDetails);
            return ResponseEntity.ok(updatedWorkOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<WorkOrder> updateWorkOrderStatus(@PathVariable Integer id, @RequestBody String status) {
        try {
            WorkOrder updatedWorkOrder = workOrderService.updateWorkOrderStatus(id, status);
            return ResponseEntity.ok(updatedWorkOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkOrder(@PathVariable Integer id) {
        try {
            workOrderService.softDeleteWorkOrder(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status/{status}")
    public List<WorkOrder> getWorkOrdersByStatus(@PathVariable String status) {
        return workOrderService.getWorkOrdersByStatus(status);
    }

    @GetMapping("/user/{userId}")
    public List<WorkOrder> getWorkOrdersByUserId(@PathVariable Integer userId) {
        return workOrderService.getWorkOrdersByUserId(userId);
    }

    @PatchMapping("/{id}/completed-date")
    public ResponseEntity<WorkOrder> updateCompletedDate(
            @PathVariable("id") Integer workOrderId,
            @RequestBody CompletedDateUpdateRequest request) {

        WorkOrder updatedWorkOrder = workOrderService.updateCompletedDate(workOrderId, request.getCompletedDate());
        return ResponseEntity.ok(updatedWorkOrder);
    }
}
