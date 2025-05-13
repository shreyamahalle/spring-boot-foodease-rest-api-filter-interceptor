package com.shreya.spring.repository;

import com.shreya.spring.model.OrderStatus;
import com.shreya.spring.service.ConnectionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class OrderStatusRepository {

    private final Logger log = LoggerFactory.getLogger(OrderStatusRepository.class);

    public boolean addorderStatus(OrderStatus orderStatus) {
        String query = "INSERT INTO order_status (status, description) VALUES (?, ?)";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, orderStatus.getStatus());
            preparedStatement.setString(2, orderStatus.getDescription());

            log.info("Adding OrderStatus: {}", orderStatus);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            log.error("Error adding OrderStatus: {}", orderStatus, e);
            throw new RuntimeException(e);
        }
    }

    public List<OrderStatus> retrieveOrderStatuses() throws SQLException {
        List<OrderStatus> orderStatuses = new ArrayList<>();
        String query = "SELECT * FROM order_status";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            log.info("Retrieving all order statuses");
            while (resultSet.next()) {
                OrderStatus orderStatus = new OrderStatus(
                        resultSet.getLong("id"),
                        resultSet.getString("status"),
                        resultSet.getString("description")
                );
                orderStatuses.add(orderStatus);
            }

        } catch (SQLException e) {
            log.error("Error retrieving order statuses", e);
            throw e;
        }

        return orderStatuses;
    }

    public OrderStatus retrieveOrderStatus(long id) throws SQLException {
        String query = "SELECT * FROM order_status WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            log.info("Retrieving OrderStatus by id: {}", id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new OrderStatus(
                        id,
                        resultSet.getString("status"),
                        resultSet.getString("description")
                );
            }

        } catch (SQLException e) {
            log.error("Error retrieving OrderStatus with id: {}", id, e);
            throw e;
        }

        return null;
    }

    public boolean updateOrderStatus(OrderStatus orderStatus) {
        String query = "UPDATE order_status SET status = ?, description = ? WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, orderStatus.getStatus());
            preparedStatement.setString(2, orderStatus.getDescription());
            preparedStatement.setLong(3, orderStatus.getId());

            log.info("Updating OrderStatus: {}", orderStatus);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            log.error("Error updating OrderStatus: {}", orderStatus, e);
            throw new RuntimeException(e);
        }
    }

    public boolean deleteOrderStatus(long id) {
        String query = "DELETE FROM order_status WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            log.info("Deleting OrderStatus with id: {}", id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            log.error("Error deleting OrderStatus with id: {}", id, e);
            throw new RuntimeException(e);
        }
    }
}
