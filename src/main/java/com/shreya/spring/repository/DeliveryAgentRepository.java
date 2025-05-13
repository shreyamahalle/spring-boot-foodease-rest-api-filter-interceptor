package com.shreya.spring.repository;

import com.shreya.spring.model.DeliveryAgent;
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
public class DeliveryAgentRepository {

    private final Logger log = LoggerFactory.getLogger(DeliveryAgentRepository.class);

    public boolean addDeliveryAgent(DeliveryAgent deliveryAgent) {
        String query = "INSERT INTO deliveryAgent (id, name, city, mobileNo) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, deliveryAgent.getId());
            ps.setString(2, deliveryAgent.getName());
            ps.setString(3, deliveryAgent.getCity());
            ps.setInt(4, deliveryAgent.getMobileNo());

            int rowsAffected = ps.executeUpdate();
            log.info("Added delivery agent: {}", deliveryAgent);
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error adding delivery agent: {}", deliveryAgent, e);
            throw new RuntimeException("Error adding delivery agent", e);
        }
    }

    public List<DeliveryAgent> retrieveDeliveryAgents() {
        List<DeliveryAgent> deliveryAgents = new ArrayList<>();
        String query = "SELECT * FROM deliveryAgent";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DeliveryAgent deliveryAgent = new DeliveryAgent(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getInt("mobileNo")
                );
                deliveryAgents.add(deliveryAgent);
            }
            log.info("Retrieved {} delivery agents", deliveryAgents.size());
        } catch (SQLException e) {
            log.error("Error retrieving delivery agents", e);
            throw new RuntimeException("Error retrieving delivery agents", e);
        }
        return deliveryAgents;
    }

    public DeliveryAgent findById(int id) {
        String query = "SELECT * FROM deliveryAgent WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DeliveryAgent deliveryAgent = new DeliveryAgent(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("city"),
                            rs.getInt("mobileNo")
                    );
                    log.info("Found delivery agent with id: {}", id);
                    return deliveryAgent;
                }
            }
        } catch (SQLException e) {
            log.error("Error finding delivery agent with id: {}", id, e);
        }
        return null; // Return null if not found
    }

    public boolean deleteDeliveryAgent(int id) {
        String query = "DELETE FROM deliveryAgent WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            log.info("Deleted delivery agent with id: {}", id);
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error deleting delivery agent with id: {}", id, e);
            throw new RuntimeException("Error deleting delivery agent", e);
        }
    }

    public boolean updateDeliveryAgent(DeliveryAgent deliveryAgent) {
        String query = "UPDATE deliveryAgent SET name = ?, city = ?, mobileNo = ? WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, deliveryAgent.getName());
            ps.setString(2, deliveryAgent.getCity());
            ps.setInt(3, deliveryAgent.getMobileNo());
            ps.setInt(4, deliveryAgent.getId());

            int rowsAffected = ps.executeUpdate();
            log.info("Updated delivery agent with id: {}", deliveryAgent.getId());
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error updating delivery agent with id: {}", deliveryAgent.getId(), e);
            throw new RuntimeException("Error updating delivery agent", e);
        }
    }
}
