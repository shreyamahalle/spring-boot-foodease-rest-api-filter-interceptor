package com.shreya.spring.exception;

public class MenuItemUpdateException extends RuntimeException {
    public MenuItemUpdateException() {
        super("ailed to update MenuItem");
    }
    public MenuItemUpdateException(String message, Throwable cause) {
        super(message);
    }

    public MenuItemUpdateException(String s) {
    }
}