package com.towermanagement.tower.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class)
public class Tower {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tower_id_seq")
    @SequenceGenerator(name = "tower_id_seq", sequenceName = "tower_id_seq", allocationSize = 1)
    private Integer tower_id;

    private Double height;

    private String type;

    private String status;

    private String location;

    private Integer pincode;

    private Double latitude;

    private Double longitude;

    private Integer power_reading;

    private BigDecimal fuel_reading;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updated_at;
    

    
       private Date last_maintained;


    private Boolean deletedStatus = false;
}
