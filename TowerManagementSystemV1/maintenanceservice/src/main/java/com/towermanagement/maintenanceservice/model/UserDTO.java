package com.towermanagement.maintenanceservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("specialisation")
    private String specialisation;

    @JsonProperty("location")
    private String location;

    @JsonProperty("pincode")
    private Integer pincode;

    @JsonProperty("role")
    private String role;

    @JsonProperty("active_status")
    private Boolean activeStatus;

    @JsonProperty("deleted_status")
    private Boolean deletedStatus;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
