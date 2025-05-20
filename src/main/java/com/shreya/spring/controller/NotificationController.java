package com.shreya.spring.controller;

import com.shreya.spring.exception.NotificationNotFoundException;
import com.shreya.spring.exception.NotificationSaveException;
import com.shreya.spring.model.Notification;
import com.shreya.spring.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificationManagement")
public class NotificationController {

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> addNotification(@RequestBody Notification notification) {
        log.info("Adding notification: {}", notification);
        try {
            boolean saved = notificationService.saveNotification(notification);
            if (saved) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Notification saved successfully.");
            } else {
                throw new NotificationSaveException("Failed to save notification", null);
            }
        } catch (Exception e) {
            throw new NotificationSaveException("Exception while saving notification", e);
        }
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAll() {
        log.info("Fetching all notifications");
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Notification>> getByCustomer(@PathVariable Long customerId) {
        log.info("Fetching notifications for customerId: {}", customerId);
        List<Notification> list = notificationService.getNotificationsByCustomerId(customerId);
        if (list.isEmpty()) {
            throw new NotificationNotFoundException("No notifications found for customer ID: " + customerId);
        }
        return ResponseEntity.ok(list);
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<String> markAsRead(@PathVariable Long id) {
        log.info("Marking notification {} as read", id);
        boolean updated = notificationService.markAsRead(id);
        if (!updated) {
            throw new NotificationNotFoundException("Notification not found with id: " + id);
        }
        return ResponseEntity.ok("Notification marked as read.");
    }
}
