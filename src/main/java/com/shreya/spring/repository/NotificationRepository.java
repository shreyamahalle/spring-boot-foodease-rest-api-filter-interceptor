package com.shreya.spring.repository;

import com.shreya.spring.model.Notification;
import com.shreya.spring.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificationRepository {

    private final Logger log = LoggerFactory.getLogger(NotificationRepository.class);

    public boolean saveNotification(Notification notification) {
        String query = "INSERT INTO notification (customer_id, message, is_read) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, notification.getCustomer_id());
            ps.setString(2, notification.getMessage());
            ps.setBoolean(3, notification.is_read());

            log.info("Saving notification: {}", notification);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            log.error("Error saving notification: {}", notification, e);
            throw new RuntimeException(e);
        }
    }

    public List<Notification> getAllNotifications() {
        String query = "SELECT * FROM notification";
        List<Notification> notifications = new ArrayList<>();

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                notifications.add(mapRowToNotification(rs));
            }

            log.info("Retrieved {} notifications", notifications.size());

        } catch (SQLException e) {
            log.error("Error retrieving all notifications", e);
            throw new RuntimeException(e);
        }

        return notifications;
    }

    public List<Notification> getNotificationsByCustomerId(Long customerId) {
        String query = "SELECT * FROM notification WHERE customer_id = ?";
        List<Notification> notifications = new ArrayList<>();

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                notifications.add(mapRowToNotification(rs));
            }

            log.info("Retrieved {} notifications for customer ID: {}", notifications.size(), customerId);

        } catch (SQLException e) {
            log.error("Error retrieving notifications for customer ID: {}", customerId, e);
            throw new RuntimeException(e);
        }

        return notifications;
    }

    public boolean markAsRead(Long id) {
        String query = "UPDATE notification SET is_read = TRUE WHERE id = ?";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);
            boolean updated = ps.executeUpdate() > 0;
            log.info("Marked notification ID {} as read: {}", id, updated);
            return updated;

        } catch (SQLException e) {
            log.error("Error marking notification ID {} as read", id, e);
            throw new RuntimeException(e);
        }
    }

    private Notification mapRowToNotification(ResultSet rs) throws SQLException {
        return new Notification(
                rs.getLong("id"),
                rs.getLong("customer_id"),
                rs.getString("message"),
                rs.getBoolean("is_read"),
                rs.getString("timestamp")
        );
    }
}
