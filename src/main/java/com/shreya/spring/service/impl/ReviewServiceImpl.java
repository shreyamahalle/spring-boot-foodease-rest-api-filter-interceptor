package com.shreya.spring.service.impl;

import com.shreya.spring.exception.ReviewAlreadyExistsException;
import com.shreya.spring.exception.ReviewDeleteException;
import com.shreya.spring.exception.ReviewNotFoundException;
import com.shreya.spring.exception.ReviewUpdateException;
import com.shreya.spring.model.Review;
import com.shreya.spring.repository.ReviewRepository;
import com.shreya.spring.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public boolean addReview(Review review) throws SQLException {
        log.info("Adding review {}", review);
        boolean added = reviewRepository.addReview(review);
        if (!added) {
            throw new ReviewAlreadyExistsException("Review already exists or failed to add.");
        }
        return true;
    }

    @Override
    public List<Review> getAllReviews() throws SQLException {
        log.info("Fetching all reviews");
        return reviewRepository.retrieveReviews();
    }

    @Override
    public Review getReviewById(Long id) {
        log.info("Getting Review By ID: {}", id);
        Review review = reviewRepository.findById(id);
        if (review == null) {
            throw new ReviewNotFoundException("Review not found with ID: " + id);
        }
        return review;
    }

    @Override
    public boolean updateReview(Review review) {
        log.info("Updating Review: {}", review);
        boolean updated = reviewRepository.updateReview(review);
        if (!updated) {
            throw new ReviewUpdateException("Failed to update review with ID: " + review.getId());
        }
        return true;
    }

    @Override
    public boolean deleteReview(Long id) {
        log.info("Deleting Review ID: {}", id);
        boolean deleted = reviewRepository.deleteReview(id);
        if (!deleted) {
            throw new ReviewDeleteException("Failed to delete review with ID: " + id);
        }
        return true;
    }
}
