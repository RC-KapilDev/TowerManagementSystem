package com.verizon.equipmentservice.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TowerDTO {

    @JsonProperty("tower_id")
    private int towerId;

    @JsonProperty("location")
    private String location;

    @JsonProperty("height")
    private double height;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("pincode")
    private int pincode;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("power_reading")
    private int powerReading;

    @JsonProperty("fuel_reading")
    private int fuelReading;
@JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private LocalDateTime updatedAt;

    @JsonProperty("last_maintained")
    private String lastMaintained;

    @JsonProperty("deleted_status")
    private boolean deletedStatus;

    
}
