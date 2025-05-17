package com.shreya.spring.service.impl;

import com.shreya.spring.exception.FeedbackCreationException;
import com.shreya.spring.exception.FeedbackNotFoundException;
import com.shreya.spring.model.Feedback;
import com.shreya.spring.repository.FeedbackRepository;
import com.shreya.spring.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final Logger log = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public boolean addFeedback(Feedback feedback) throws SQLException {
        log.info("Saving feedback: {}", feedback);
        boolean result = feedbackRepository.addFeedback(feedback);
        if (!result) {
            throw new FeedbackCreationException("Unable to add feedback.");
        }
        return result;
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        log.info("Fetching all feedbacks");
        return feedbackRepository.retrieveFeedbacks();
    }

    @Override
    public Optional<Feedback> getFeedbackById(Long id) {
        log.info("Fetching feedback by id: {}", id);
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if (feedback.isEmpty()) {
            throw new FeedbackNotFoundException("Feedback not found with ID: " + id);
        }
        return feedback;
    }

    @Override
    public boolean deleteFeedback(Long id) {
        log.info("Deleting feedback by id: {}", id);
        boolean result = feedbackRepository.deleteFeedback(id);
        if (!result) {
            throw new FeedbackNotFoundException("Cannot delete. Feedback not found with ID: " + id);
        }
        return result;
    }

    @Override
    public boolean updateFeedback(Feedback feedback) {
        log.info("Updating feedback: {}", feedback);
        boolean result = feedbackRepository.updateFeedback(feedback);
        if (!result) {
            throw new FeedbackNotFoundException("Cannot update. Feedback not found with ID: " + feedback.getId());
        }
        return result;
    }
}
