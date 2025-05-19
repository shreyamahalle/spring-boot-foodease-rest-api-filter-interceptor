package com.shreya.spring.service.impl;

import com.shreya.spring.model.Notification;
import com.shreya.spring.repository.NotificationRepository;
import com.shreya.spring.service.NotificationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public boolean saveNotification(Notification notification) {
        return notificationRepository.saveNotification(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.getAllNotifications();
    }

    @Override
    public List<Notification> getNotificationsByCustomerId(Long customerId) {
        return notificationRepository.getNotificationsByCustomerId(customerId);
    }

    @Override
    public boolean markAsRead(Long id) {
        return notificationRepository.markAsRead(id);
    }
}
