package com.tower.workorderservice.entity;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TowerDTO {

    @JsonProperty("tower_id")
    private Integer towerId;

    @JsonProperty("location")
    private String location;

    @JsonProperty("height")
    private double height;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("pincode")
    private String pincode;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("power_reading")
    private int powerReading;

    @JsonProperty("fuel_reading")
    private int fuelReading;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("last_maintained")
    private LocalDateTime lastMaintained;

    @JsonProperty("deleted_status")
    private String deletedStatus;
}
