package com.verizon.equipmentservice.entity;

import lombok.Data;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipment_id_seq")
    @SequenceGenerator(name = "equipment_id_seq", sequenceName = "equipment_id_seq", allocationSize = 1)
    @Column(name = "equipment_id")
    private Integer equipmentId;

    @Column(name = "workorder_id", nullable = false)
    private Integer workorderId;

    @Column(name = "tower_id", nullable = false)
    private Integer towerId;

    @Column(name = "serial_number")
    private Integer serialNumber;

    @Column(name = "manufacture")
    private String manufacture;

    @Column(name = "model")
    private String model;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "deleted_status", nullable = false)
    private Boolean deletedStatus = false;

    @Column(name = "claimed")
    private Boolean claimed = false;
}

