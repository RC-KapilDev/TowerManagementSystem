package com.verizon.equipmentservice.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;


import java.time.LocalDateTime;

@Data
public class TowerDTO {
    
    @JsonProperty("created_at")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", timezone = "GMT")
    private LocalDateTime createdAt;

    @JsonProperty("deleted_status")
    private boolean deletedStatus;

    @JsonProperty("fuel_reading")
    private String fuelReading;

    @JsonProperty("height")
    private double height;

    @JsonProperty("last_maintained")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", timezone = "GMT")
    private LocalDateTime lastMaintained;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("location")
    private String location;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("pincode")
    private int pincode;

    @JsonProperty("power_reading")
    private double powerReading;

    @JsonProperty("status")
    private String status;

    @JsonProperty("tower_id")
    private int towerId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", timezone = "GMT")
    private LocalDateTime updatedAt;

    // Getters and Setters
}
