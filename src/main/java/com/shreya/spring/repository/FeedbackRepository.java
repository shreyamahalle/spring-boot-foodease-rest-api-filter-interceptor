package com.shreya.spring.repository;

import com.shreya.spring.model.Customer;
import com.shreya.spring.model.Feedback;
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
import java.util.Optional;

@Repository
public class FeedbackRepository {

    private final Logger log = LoggerFactory.getLogger(FeedbackRepository.class);

    public boolean addFeedback(Feedback feedback) {
        String query = "INSERT INTO feedback (customer_id, order_id, rating, comment, feedback_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, feedback.getCustomer().getId());
            ps.setLong(2, feedback.getOrder().getId());
            ps.setInt(3, feedback.getRating());
            ps.setString(4, feedback.getComment());
            ps.setString(5, feedback.getFeedbackDate());

            int rowsAffected = ps.executeUpdate();
            log.info("Feedback added: {}", feedback);
            return rowsAffected > 0;

        } catch (SQLException e) {
            log.error("Error adding feedback: {}", feedback, e);
            throw new RuntimeException("Error adding feedback", e);
        }
    }

    public List<Feedback> retrieveFeedbacks() {
        List<Feedback> feedbacks = new ArrayList<>();
        String query = "SELECT * FROM feedback";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Feedback feedback = mapRowToFeedback(rs);
                feedbacks.add(feedback);
            }
            log.info("Retrieved {} feedback records", feedbacks.size());

        } catch (SQLException e) {
            log.error("Error retrieving feedback records", e);
            throw new RuntimeException("Error retrieving feedback records", e);
        }

        return feedbacks;
    }

    public Optional<Feedback> findById(Long id) {
        String query = "SELECT * FROM feedback WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Feedback feedback = mapRowToFeedback(rs);
                    return Optional.of(feedback);
                }
            }
        } catch (SQLException e) {
            log.error("Error finding feedback with id: {}", id, e);
        }
        return Optional.empty();
    }

    public boolean deleteFeedback(Long id) {
        String query = "DELETE FROM feedback WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();
            log.info("Deleted feedback with id: {}", id);
            return rowsAffected > 0;

        } catch (SQLException e) {
            log.error("Error deleting feedback with id: {}", id, e);
            throw new RuntimeException("Error deleting feedback", e);
        }
    }

    public boolean updateFeedback(Feedback feedback) {
        String query = "UPDATE feedback SET customer_id = ?, order_id = ?, rating = ?, comment = ?, feedback_date = ? WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, feedback.getCustomer().getId());
            ps.setLong(2, feedback.getOrder().getId());
            ps.setInt(3, feedback.getRating());
            ps.setString(4, feedback.getComment());
            ps.setString(5, feedback.getFeedbackDate());
            ps.setLong(6, feedback.getId());

            int rowsAffected = ps.executeUpdate();
            log.info("Updated feedback with id: {}", feedback.getId());
            return rowsAffected > 0;

        } catch (SQLException e) {
            log.error("Error updating feedback with id: {}", feedback.getId(), e);
            throw new RuntimeException("Error updating feedback", e);
        }
    }

    private Feedback mapRowToFeedback(ResultSet rs) throws SQLException {
        return Feedback.builder()
                .id(rs.getLong("id"))
                .customer(Customer.builder().id((int) rs.getLong("customer_id")).build())
                .order(Order.builder().id((int) rs.getLong("order_id")).build())
                .rating(rs.getInt("rating"))
                .comment(rs.getString("comment"))
                .feedbackDate(rs.getString("feedback_date"))
                .build();
    }
}
