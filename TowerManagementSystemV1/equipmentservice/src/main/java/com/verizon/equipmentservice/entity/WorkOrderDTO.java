package com.verizon.equipmentservice.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;





@Data
public class WorkOrderDTO {

    @JsonProperty("workorder_id")
    private Integer workOrderId;

    @JsonProperty("tower")
    private Integer towerId;

    @JsonProperty("user")
    private Integer userId;

    @JsonProperty("task_description")
    private String taskDescription;

    @JsonProperty("scheduled_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduledDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("completed_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate completedDate;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime updatedAt;

    @JsonProperty("deleted_status")
    private boolean deletedStatus;
}