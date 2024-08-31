package com.towermanagement.notificationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.towermanagement.notificationservice.model.Notification;

@Repository


public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByReceiverIdAndDeletedStatusFalseOrderBySentAtDesc(Integer receiverId);
    List<Notification> findByReceiverIdAndReadStatusAndDeletedStatusFalseOrderBySentAtDesc(Integer receiverId, Boolean readStatus);
}

