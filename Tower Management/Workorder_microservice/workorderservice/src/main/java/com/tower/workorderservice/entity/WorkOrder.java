package com.tower.workorderservice.entity;


import lombok.Data;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "work_order")
@Data
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workorder_id_seq")
    @SequenceGenerator(name = "workorder_id_seq", sequenceName = "workorder_id_seq", allocationSize = 1)
    private Integer workorderId;

    @Column(name = "tower_id", nullable = false)
    private Integer towerId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "scheduled_date")
    private LocalDate scheduledDate;

    @Column(name = "status")
    private String status;

    @Column(name = "completed_date")
    private LocalDateTime completedDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_status")
    private Boolean deletedStatus;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}