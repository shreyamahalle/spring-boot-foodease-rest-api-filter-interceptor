package com.shreya.spring.service;

import com.shreya.spring.model.Review;

import java.sql.SQLException;
import java.util.List;

public interface ReviewService {

    boolean addReview(Review review) throws SQLException;

    List<Review> getAllReviews() throws SQLException;

    Review getReviewById(Long id);

    boolean updateReview(Review review);

    boolean deleteReview(Long id);
}
