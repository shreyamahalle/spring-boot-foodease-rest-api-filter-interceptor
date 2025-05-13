package com.shreya.spring.controller;

import com.shreya.spring.model.Feedback;
import com.shreya.spring.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean addFeedback(@RequestBody Feedback feedback) throws SQLException {
        log.info("Received request to add feedback: {}", feedback);
        return feedbackService.addFeedback(feedback);
    }

    @GetMapping("/feedback")
    public List<Feedback> getAllFeedbacks() {
        log.info("Received request to fetch all feedbacks");
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/feedback/{id}")
    public Optional<Feedback> getFeedbackById(@PathVariable Long id) {
        log.info("Received request to fetch feedback by id: {}", id);
        return feedbackService.getFeedbackById(id);
    }

    @DeleteMapping("/feedback/{id}")
    public boolean deleteFeedback(@PathVariable Long id) {
        log.info("Received request to delete feedback with id: {}", id);
        return feedbackService.deleteFeedback(id);
    }

    @PutMapping("/feedback")
    public boolean updateFeedback(@RequestBody Feedback feedback) {
        log.info("Received request to update feedback: {}", feedback);
        return feedbackService.updateFeedback(feedback);
    }
}
