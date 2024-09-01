package com.towermanagement.workorderservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TowerDTO {

    @JsonProperty("tower_id")
    private Integer towerId;

    @JsonProperty("height")
    private Double height;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("location")
    private String location;

    @JsonProperty("pincode")
    private Integer pincode;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("power_reading")
    private Double powerReading;

    @JsonProperty("fuel_reading")
    private Double fuelReading; // Adjusted from String to Double

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
    private boolean deletedStatus; // Adjusted property name to match JSON field
}




