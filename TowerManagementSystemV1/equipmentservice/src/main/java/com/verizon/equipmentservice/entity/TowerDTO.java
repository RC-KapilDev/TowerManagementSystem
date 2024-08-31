package com.verizon.equipmentservice.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalDate;



@Data
public class TowerDTO {
    
    @JsonProperty("tower_id")
    private int towerId;

    @JsonProperty("height")
    private double height;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("location")
    private String location;

    @JsonProperty("pincode")
    private int pincode;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("power_reading")
    private double powerReading;

    @JsonProperty("fuel_reading")
    private String fuelReading;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime updatedAt;

    @JsonProperty("last_maintained")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastMaintained;

    @JsonProperty("deleted_status")
    private boolean deletedStatus;

    // Getters and Setters
}
