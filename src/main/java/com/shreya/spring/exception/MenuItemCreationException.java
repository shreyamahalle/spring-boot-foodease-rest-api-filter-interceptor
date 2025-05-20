package com.shreya.spring.exception;

public class MenuItemCreationException extends RuntimeException {
    public MenuItemCreationException() {
        super("Failed to create MenuItem");
    }

    public MenuItemCreationException(String message) {
        super(message);
    }
}