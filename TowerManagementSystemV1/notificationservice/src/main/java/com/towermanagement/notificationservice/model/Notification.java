package com.towermanagement.notificationservice.model;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_seq")
    @SequenceGenerator(name = "notification_id_seq", sequenceName = "notification_id_seq", allocationSize = 1)
    @Column(name="notification_id")
    private Integer notificationId;

    @Column(nullable = false,name="sender_id")
    private Integer senderId;

    @Column(nullable = false,name="receiver_id")
    private Integer receiverId;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false,name="sent_at")
    private LocalDateTime sentAt = LocalDateTime.now();

    @Column(nullable = false,name="read_status")
    private Boolean readStatus = false;

    @Column(nullable = false,name="deleted_status")
    private Boolean deletedStatus = false;

    // Getters and Setters
}

