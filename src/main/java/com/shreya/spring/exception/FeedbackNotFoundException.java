package com.shreya.spring.exception;

public class FeedbackNotFoundException extends RuntimeException {
    public FeedbackNotFoundException() {
        super("bad request");
    }

    public FeedbackNotFoundException(String message) {
        super(message);
    }
}

