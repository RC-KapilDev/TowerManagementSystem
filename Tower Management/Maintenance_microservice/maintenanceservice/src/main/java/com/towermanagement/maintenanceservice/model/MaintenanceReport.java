package com.towermanagement.maintenanceservice.model;



import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "maintenance_report")
public class MaintenanceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maintenance_id_seq")
    @SequenceGenerator(name = "maintenance_id_seq", sequenceName = "maintenance_id_seq", allocationSize = 1)
    @Column(name = "maintenance_id")
    private Integer maintenanceId;

    
    @Column(name = "user_id", nullable = false)
    private Integer user;

    
    @Column(name = "workorder_id", nullable = false)
    private Integer workOrder;

   
    @Column(name = "tower_id", nullable = false)
    private Integer towerInfo;

    @Column(name = "equipment_required", columnDefinition = "TEXT")
    private String equipmentRequired;

    @Column(name = "issues_faced", columnDefinition = "TEXT")
    private String issuesFaced;

    @Column(name = "priority", columnDefinition = "TEXT")
    private String priority;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "deleted_status", nullable = false)
    private Boolean deletedStatus = false;

    // Getters and Setters

}