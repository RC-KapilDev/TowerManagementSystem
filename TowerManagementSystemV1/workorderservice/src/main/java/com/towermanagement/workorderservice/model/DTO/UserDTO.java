package com.towermanagement.workorderservice.model.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;


import java.time.LocalDateTime;


@Data


public class UserDTO {
    
    @JsonProperty("active_status")
    private boolean activeStatus;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", timezone = "GMT")
    private LocalDateTime createdAt;

    @JsonProperty("deleted_status")
    private boolean deletedStatus;

    @JsonProperty("email")
    private String email;

    @JsonProperty("location")
    private String location;

    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;

    @JsonProperty("pincode")
    private int pincode;

    @JsonProperty("role")
    private String role;

    @JsonProperty("specialisation")
    private String specialisation;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", timezone = "GMT")
    private LocalDateTime updatedAt;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("username")
    private String username;

    // Getters and Setters
}
