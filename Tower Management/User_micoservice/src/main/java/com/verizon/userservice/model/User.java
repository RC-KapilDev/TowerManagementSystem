package com.verizon.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1, initialValue = 500001)
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String specialization;
    private String location;
    private String pincode;
    private String role;
    private Boolean activeStatus;
    private Boolean deletedStatus;
    private String name;
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updatedAt;
}
