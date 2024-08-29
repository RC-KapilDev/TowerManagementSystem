package com.towermanagement.tower.model;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tower_info")
@Data
@Getter
@Setter
public class Tower {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tower_id_seq")
    @SequenceGenerator(name = "tower_id_seq", sequenceName = "tower_id_seq", allocationSize = 1)
    private Integer tower_id;

    private Double height;
    
    private String type;
    
    @Enumerated(EnumType.STRING)
    private TowerStatus status;

    private String location;

    private Integer pincode;

    private Double latitude;

    private Double longitude;

    private Integer power_reading;

    private Double fuel_reading;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private Date last_maintained;

    private Boolean deleted_status;

    // Getters and Setters


}

    

