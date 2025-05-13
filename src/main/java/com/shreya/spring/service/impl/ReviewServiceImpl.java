package com.shreya.spring.service.impl;

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
        log.info("add review {}", review);
        return reviewRepository.addReview(review);
    }

    @Override
    public List<Review> getAllReviews() throws SQLException {
        log.info("all review");
        return reviewRepository.retrieveReviews();
    }

    @Override
    public Review getReviewById(Long id) {
        log.info("get Review ById {}", id);
        return reviewRepository.findById(id);
    }

    @Override
    public boolean updateReview(Review review) {
        log.info("Update Review {}", review);
        return reviewRepository.updateReview(review);
    }

    @Override
    public boolean deleteReview(Long id) {
        log.info("Delete Review {}", id);
        return reviewRepository.deleteReview(id);
    }
}
