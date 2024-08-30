package com.towermanagement.maintenanceservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TowerDTO {

   
    @JsonProperty("deleted_status")
    private String deletedStatus;

    @JsonProperty("fuel_reading")
    private int fuelReading;

    private int height;

    @JsonProperty("last_maintained")
    private LocalDate lastMaintained;

    private double latitude;

    private String location;

    private double longitude;

    private int pincode;

    @JsonProperty("power_reading")
    private int powerReading;

    private String status;

    @JsonProperty("tower_id")
    private int towerId;

    private String type;
@JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private LocalDateTime updatedAt;
}
