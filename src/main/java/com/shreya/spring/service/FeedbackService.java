package com.shreya.spring.service;

import com.shreya.spring.model.Feedback;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    boolean addFeedback(Feedback feedback) throws SQLException;

    List<Feedback> getAllFeedbacks();

    Optional<Feedback> getFeedbackById(Long id);

    boolean deleteFeedback(Long id);

    boolean updateFeedback(Feedback feedback);
}
