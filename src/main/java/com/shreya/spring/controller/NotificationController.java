package com.shreya.spring.controller;

import com.shreya.spring.model.Notification;
import com.shreya.spring.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/notificationManagement")
public class NotificationController {

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public boolean addNotification(@RequestBody Notification notification) throws SQLException {
        log.info("Adding new notification: {}", notification);
        return notificationService.saveNotification(notification);
    }

    @GetMapping
    public List<Notification> getAll() {
        log.info("Fetching all notifications");
        return notificationService.getAllNotifications();
    }

    @GetMapping("/customer/{customerId}")
    public List<Notification> getByCustomer(@PathVariable Long customerId) {
        log.info("Fetching notifications for customer ID: {}", customerId);
        return notificationService.getNotificationsByCustomerId(customerId);
    }

    @PutMapping("/read/{id}")
    public boolean markAsRead(@PathVariable Long id) {
        log.info("Marking notification as read with ID: {}", id);
        return notificationService.markAsRead(id);
    }
}
