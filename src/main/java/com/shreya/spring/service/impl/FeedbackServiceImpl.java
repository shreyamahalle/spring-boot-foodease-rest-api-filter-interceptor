package com.shreya.spring.service.impl;

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
        log.info("Saving feedback{}", feedback);
        return feedbackRepository.addFeedback(feedback);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        log.info("all feedbacks");
        return feedbackRepository.retrieveFeedbacks();
    }

    @Override
    public Optional<Feedback> getFeedbackById(Long id) {
        log.info("get Feedback ById {}", id);
        return feedbackRepository.findById(id);
    }

    @Override
    public boolean deleteFeedback(Long id) {
        log.info("delete Feedback ById {}", id);
        return feedbackRepository.deleteFeedback(id);
    }

    @Override
    public boolean updateFeedback(Feedback feedback) {
        log.info("update Feedback  {}", feedback);
        return feedbackRepository.updateFeedback(feedback);
    }
}
