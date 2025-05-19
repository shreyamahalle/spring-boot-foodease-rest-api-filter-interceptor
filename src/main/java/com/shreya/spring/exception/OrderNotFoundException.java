package com.shreya.spring.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
//    @ExceptionHandler(OrderNotFoundException.class)
//    public ResponseEntity<String> handleOrderNotFound(OrderNotFoundException ex) {
//        return ResponseEntity.status(404).body("Order Error: " + ex.getMessage());
//    }
}
