package com.towermanagement.notificationservice.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.towermanagement.notificationservice.model.Notification;
import com.towermanagement.notificationservice.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserService userService;

    public List<Notification> getNotificationsForUser(Integer userId) {
        userService.validateUserExists(userId);
        return notificationRepository.findByReceiverIdAndDeletedStatusFalseOrderBySentAtDesc(userId);
    }

    public Notification sendNotification(Notification notification) {
        userService.validateUserExists(notification.getReceiverId());
        userService.validateUserExists(notification.getSenderId());
        return notificationRepository.save(notification);
    }

    public Optional<Notification> updateNotification(Long id, Notification notificationDetails) {
        userService.validateUserExists(notificationDetails.getReceiverId());
        Optional<Notification> notification = notificationRepository.findById(id);
        notification.ifPresent(n -> {
            n.setReadStatus(notificationDetails.getReadStatus());
            notificationRepository.save(n);
        });
        return notification;
    }

    public void deleteNotification(Long id) {
        notificationRepository.findById(id).ifPresent(notification -> {
            userService.validateUserExists(notification.getReceiverId());
            notification.setDeletedStatus(true);
            notificationRepository.save(notification);
        });
    }

    public List<Notification> getNotificationsByStatus(Integer userId, Boolean status) {
        userService.validateUserExists(userId);
        return notificationRepository.findByReceiverIdAndReadStatusAndDeletedStatusFalseOrderBySentAtDesc(userId, status);
    }
}