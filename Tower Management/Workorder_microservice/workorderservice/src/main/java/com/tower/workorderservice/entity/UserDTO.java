package com.tower.workorderservice.entity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

    @JsonProperty("user_id")
    private Long userId;

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

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialisation() {
        return specialisation;
    }

}
