package com.shreya.spring.exception;

public class DeliveryAgentNotFoundException extends RuntimeException {
    public DeliveryAgentNotFoundException(String message) {
        super(message);
    }
}