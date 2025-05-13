package com.shreya.spring.repository;

import com.shreya.spring.model.Customer;
import com.shreya.spring.model.Restaurant;
import com.shreya.spring.model.Review;
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
public class ReviewRepository {

    private final Logger log = LoggerFactory.getLogger(ReviewRepository.class);

    public boolean addReview(Review review) {
        String query = "INSERT INTO review (rating, comment, review_date, customerid, restaurantid ) VALUES (? , ? , ? , ? , ? )";

        log.info("Adding review: {}", review);

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, review.getRating());
            ps.setString(2, review.getComment());
            ps.setTimestamp(3, new java.sql.Timestamp(review.getReviewDate().getTime()));
            ps.setLong(4, review.getCustomer().getId());
            ps.setLong(5, review.getRestaurant().getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Review> retrieveReviews() {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM review";

        log.info("Retrieving all reviews");

        try (Connection con = ConnectionService.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Review review = Review.builder()
                        .id(rs.getLong("id"))
                        .rating(rs.getInt("rating"))
                        .comment(rs.getString("comment"))
                        .reviewDate(rs.getTimestamp("review_date"))
                        .customer(Customer.builder().id(rs.getInt("customerid")).build())
                        .restaurant(Restaurant.builder().id(rs.getInt("restaurantid")).build())
                        .build();

                reviews.add(review);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public Review findById(Long id) {
        String query = "SELECT * FROM review WHERE id = ?";
        log.info("Fetching review with ID: {}", id);

        try (Connection con = ConnectionService.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Review.builder()
                        .id(rs.getLong("id"))
                        .rating(rs.getInt("rating"))
                        .comment(rs.getString("comment"))
                        .reviewDate(rs.getTimestamp("review_date"))
                        .customer(Customer.builder().id(rs.getInt("customerid")).build())
                        .restaurant(Restaurant.builder().id(rs.getInt("restaurantid")).build())
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteReview(Long id) {
        String query = "DELETE FROM review WHERE id = ?";
        log.info("Deleting review with ID: {}", id);

        try (Connection con = ConnectionService.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateReview(Review review) {
        if (review.getId() == null) {
            throw new IllegalArgumentException("Review ID is required for update");
        }

        String query = "UPDATE review SET rating = ?, comment = ?, review_date = ?, customerid = ?, restaurantid = ? WHERE id = ?";

        log.info("Updating review: {}", review);

        try (Connection con = ConnectionService.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, review.getRating());
            ps.setString(2, review.getComment());
            ps.setTimestamp(3, new java.sql.Timestamp(review.getReviewDate().getTime()));
            ps.setLong(4, review.getCustomer().getId());
            ps.setLong(5, review.getRestaurant().getId());
            ps.setLong(6, review.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

