package com.shreya.spring.controller;

import com.shreya.spring.model.Review;
import com.shreya.spring.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/reviewManagement")
public class ReviewController {

    private final Logger log = LoggerFactory.getLogger(ReviewController.class);
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review")
    public boolean addReview(@RequestBody Review review) throws SQLException {
        log.info("Adding review: {}", review);
        return reviewService.addReview(review);
    }

    @GetMapping("/review")
    public List<Review> getAllReviews() throws SQLException {
        log.info("Fetching all reviews");
        return reviewService.getAllReviews();
    }

    @GetMapping("/review/{id}")
    public Review getReviewById(@PathVariable Long id) {
        log.info("Fetching review by ID: {}", id);
        return reviewService.getReviewById(id);
    }

    @PutMapping("/review/{id}")
    public boolean updateReview(@PathVariable Long id, @RequestBody Review review) {
        review.setId(id);
        log.info("Updating review with ID: {}", id);
        return reviewService.updateReview(review);
    }

    @DeleteMapping("/review/{id}")
    public boolean deleteReview(@PathVariable Long id) {
        log.info("Deleting review with ID: {}", id);
        return reviewService.deleteReview(id);
    }
}
