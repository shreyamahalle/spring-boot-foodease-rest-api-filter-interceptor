package com.shreya.spring.controller;

import com.shreya.spring.exception.FeedbackCreationException;
import com.shreya.spring.exception.FeedbackNotFoundException;
import com.shreya.spring.model.Feedback;
import com.shreya.spring.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final Logger log = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/feedback")
    public ResponseEntity<String> addFeedback(@RequestBody Feedback feedback) {
        try {
            log.info("Received request to add feedback: {}", feedback);
            feedbackService.addFeedback(feedback);
            return ResponseEntity.ok("Feedback added successfully");
        } catch (FeedbackCreationException | SQLException e) {
            log.error("Error adding feedback: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/feedback")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        log.info("Received request to fetch all feedbacks");
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    @GetMapping("/feedback/{id}")
    public ResponseEntity<?> getFeedbackById(@PathVariable Long id) {
        try {
            log.info("Received request to fetch feedback by id: {}", id);
            Optional<Feedback> feedback = feedbackService.getFeedbackById(id);
            return ResponseEntity.ok(feedback.get());
        } catch (FeedbackNotFoundException e) {
            log.error("Feedback not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
        try {
            log.info("Received request to delete feedback with id: {}", id);
            feedbackService.deleteFeedback(id);
            return ResponseEntity.ok("Feedback deleted successfully");
        } catch (FeedbackNotFoundException e) {
            log.error("Feedback deletion failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/feedback")
    public ResponseEntity<String> updateFeedback(@RequestBody Feedback feedback) {
        try {
            log.info("Received request to update feedback: {}", feedback);
            feedbackService.updateFeedback(feedback);
            return ResponseEntity.ok("Feedback updated successfully");
        } catch (FeedbackNotFoundException e) {
            log.error("Feedback update failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
