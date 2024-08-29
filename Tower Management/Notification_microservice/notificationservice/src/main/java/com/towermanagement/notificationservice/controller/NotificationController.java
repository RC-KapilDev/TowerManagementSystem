package com.towermanagement.notificationservice.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.towermanagement.notificationservice.model.Notification;
import com.towermanagement.notificationservice.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> getNotifications(@RequestParam Integer userId) {
        try {
            return notificationService.getNotificationsForUser(userId);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PostMapping
    public Notification sendNotification(@RequestBody Notification notification) {
        try {
            return notificationService.sendNotification(notification);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PutMapping("/{id}")
    public Optional<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notificationDetails) {
        try {
            return notificationService.updateNotification(id, notificationDetails);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        try {
            notificationService.deleteNotification(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getNotificationsByUserId(@PathVariable Integer userId) {
        return notificationService.getNotificationsForUser(userId);
    }

    @GetMapping("/status/{status}")
    public List<Notification> getNotificationsByStatus(@RequestParam Integer userId, @PathVariable Boolean status) {
        try {
            return notificationService.getNotificationsByStatus(userId, status);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
