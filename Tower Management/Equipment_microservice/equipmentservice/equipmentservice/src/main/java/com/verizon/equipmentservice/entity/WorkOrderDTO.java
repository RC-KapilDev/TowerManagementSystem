package com.verizon.equipmentservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;




@Data
public class WorkOrderDTO {
    
    @JsonProperty("workorder_id")
    private int workorderId;
    
    @JsonProperty("tower_id")
    private int towerId;
    
    @JsonProperty("user_id")
    private int userId;
    
    @JsonProperty("task_description")
    private String taskDescription;
    
    @JsonProperty("scheduled_date")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", timezone = "GMT")
    private String scheduledDate;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("completed_date")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", timezone = "GMT")
    private String completedDate;
    
    @JsonProperty("created_at")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", timezone = "GMT")
    private String createdAt;
    
    @JsonProperty("updated_at")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", timezone = "GMT")
    private String updatedAt;
    
    @JsonProperty("deleted_status")
    private boolean deletedStatus;
}