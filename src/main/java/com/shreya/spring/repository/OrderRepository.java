package com.shreya.spring.repository;

import com.shreya.spring.model.Order;
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
public class OrderRepository {

    private final Logger log = LoggerFactory.getLogger(OrderRepository.class);

    public boolean addOrder(Order order) {
        String query = "INSERT INTO orderr (id, type, note, paymentMethod) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, order.getId());
            preparedStatement.setString(2, order.getType());
            preparedStatement.setString(3, order.getNote());
            preparedStatement.setString(4, order.getPaymentMethod());

            log.info("Adding Order: {}", order);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            log.error("Error adding order: {}", order, e);
            throw new RuntimeException(e);
        }
    }

    public List<Order> retrieveOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orderr";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            log.info("Retrieving all orders");

            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getString("type"),
                        resultSet.getString("note"),
                        resultSet.getString("paymentMethod")
                );
                orders.add(order);
            }

        } catch (SQLException e) {
            log.error("Error retrieving orders", e);
            throw new RuntimeException(e);
        }

        return orders;
    }

    public Order retrieveOrder(int id, String type) {
        String query = "SELECT * FROM orderr WHERE id = ? AND type = ?";
        Order order = null;

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, type);

            log.info("Retrieving order with ID: {} and Type: {}", id, type);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new Order(
                        id,
                        type,
                        resultSet.getString("note"),
                        resultSet.getString("paymentMethod")
                );
            }

        } catch (SQLException e) {
            log.error("Error retrieving order with ID: {} and Type: {}", id, type, e);
            throw new RuntimeException(e);
        }

        return order;
    }

    public boolean deleteOrder(int id) {
        String query = "DELETE FROM orderr WHERE id = ?";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            log.info("Deleting order with ID: {}", id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            log.error("Error deleting order with ID: {}", id, e);
            throw new RuntimeException(e);
        }
    }

    public boolean updateOrder(int id, String type) {
        String query = "UPDATE orderr SET type = ? WHERE id = ?";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, type);
            preparedStatement.setInt(2, id);

            log.info("Updating order ID {} with new type: {}", id, type);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            log.error("Error updating order ID: {}", id, e);
            throw new RuntimeException(e);
        }
    }
}
