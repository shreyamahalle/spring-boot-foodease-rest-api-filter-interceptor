package com.shreya.spring.exception;

public class MenuItemDeletionException extends RuntimeException {
    public MenuItemDeletionException() {
        super("Failed to delete MenuItem");
    }

    public MenuItemDeletionException(String message) {
        super(message);
    }
}