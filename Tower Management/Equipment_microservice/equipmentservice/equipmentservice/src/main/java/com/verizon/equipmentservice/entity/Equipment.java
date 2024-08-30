package com.verizon.equipmentservice.entity;

import lombok.Data;
//import javax.persistence.*;

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
@Table(name = "/equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipment_id_seq")
    @SequenceGenerator(name = "equipment_id_seq", sequenceName = "equipment_id_seq", allocationSize = 1)
    private Integer equipmentId;

    @Column(nullable = false)
    private Integer workorderId;

    @Column(nullable = false)
    private Integer towerId;

    private Integer serialNumber;
    private String manufacture;
    private String model;
    private String equipmentName;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean deletedStatus = false;

    @Column(nullable = false)
    private Boolean claimed = false;
}
