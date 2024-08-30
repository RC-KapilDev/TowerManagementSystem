package com.towermanagement.maintenanceservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class WorkorderDTO {

    @JsonProperty("workorder_id")
    private Integer workOrderId;

    @JsonProperty("tower_id")
    private Integer towerId;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("task_description")
    private String taskDescription;

    @JsonProperty("scheduled_date")
    private String scheduledDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("completed_date")
    private String completedDate;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("deleted_status")
    private String deletedStatus;
}
