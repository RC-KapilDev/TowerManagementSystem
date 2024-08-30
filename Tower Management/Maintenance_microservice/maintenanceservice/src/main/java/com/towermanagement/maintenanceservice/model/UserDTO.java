package com.towermanagement.maintenanceservice.model;


    import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @JsonProperty("active_status")
    private boolean activeStatus;

    @JsonProperty("created_at")
    private String createdAt;

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
    private String updatedAt;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("username")
    private String username;
    
}
