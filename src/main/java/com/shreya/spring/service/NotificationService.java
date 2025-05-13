package com.shreya.spring.service;

import com.shreya.spring.model.Notification;

import java.sql.SQLException;
import java.util.List;

public interface NotificationService {

    boolean saveNotification(Notification notification) throws SQLException;

    List<Notification> getAllNotifications();

    List<Notification> getNotificationsByCustomerId(Long customerId);

    boolean markAsRead(Long id);
}
