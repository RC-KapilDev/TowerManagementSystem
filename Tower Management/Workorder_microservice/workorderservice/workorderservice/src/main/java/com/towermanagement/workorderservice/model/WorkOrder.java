package com.towermanagement.workorderservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "work_order")
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workorder_id_seq")
    @SequenceGenerator(name = "workorder_id_seq", sequenceName = "workorder_id_seq", allocationSize = 1)
    @Column(name = "workorder_id", updatable = false, nullable = false)
    private Integer workorderId;

   
   @Column(name = "tower_id", nullable = false)
    private Integer tower;

  
    @Column(name = "user_id", nullable = false)
    private Integer user;

    @Column(name = "task_description", columnDefinition = "TEXT")
    private String taskDescription;

    @Column(name = "scheduled_date", columnDefinition = "DATE", nullable = false)
    private LocalDate scheduledDate = LocalDate.now();

   
    @Column(name = "status", nullable = false)
    private String status = "PENDING";

    @Column(name = "completed_date")
    private LocalDateTime completedDate;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "deleted_status", nullable = false)
    private Boolean deletedStatus = false;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}


