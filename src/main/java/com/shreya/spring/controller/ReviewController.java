package com.shreya.spring.controller;

import com.shreya.spring.model.Review;
import com.shreya.spring.service.ReviewService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
    public ResponseEntity<String> addReview(@RequestBody Review review) throws SQLException {
        log.info("Adding review: {}", review);
        reviewService.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review added successfully!");
    }

    @GetMapping("/review")
    public ResponseEntity<List<Review>> getAllReviews() throws SQLException {
        log.info("Fetching all reviews");
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        log.info("Fetching review by ID: {}", id);
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @PutMapping("/review/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id, @RequestBody Review review) {
        review.setId(id);
        log.info("Updating review with ID: {}", id);
        reviewService.updateReview(review);
        return ResponseEntity.ok("Review updated successfully!");
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        log.info("Deleting review with ID: {}", id);
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review deleted successfully!");
    }
}
