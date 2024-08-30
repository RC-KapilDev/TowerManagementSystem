package com.verizon.equipmentservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class WorkOrderDTO {

    @JsonProperty("workorder_id")
    private int workorderId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private String status;

    @JsonProperty("assigned_to")
    private String assignedTo;

    @JsonProperty("priority")
    private String priority;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("completed_at")
    private LocalDateTime completedAt;

    // Getters and Setters
    public int getWorkorderId() {
        return workorderId;
    }

    public void setWorkorderId(int workorderId) {
        this.workorderId = workorderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}