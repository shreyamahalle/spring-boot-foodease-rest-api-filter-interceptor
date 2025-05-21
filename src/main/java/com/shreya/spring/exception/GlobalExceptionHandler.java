package com.shreya.spring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle BookingNotAddedException
    @ExceptionHandler(value = BookingAddFailedException.class)
    public ResponseEntity<String> handleBookingNotAddedException(BookingAddFailedException ex) {
        log.error("BookingNotAddedException: {}", ex.getMessage());
        return new ResponseEntity<>("Booking not added", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle NoBookingTablesFoundException
    @ExceptionHandler(value = NoBookingTablesFoundException.class)
    public ResponseEntity<Object> handleNoBookingTablesFoundException(NoBookingTablesFoundException ex) {
        log.warn("NoBookingTablesFoundException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Handle generic RuntimeException
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        log.error("RuntimeException: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error. Please try again later.");
    }

    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<String> handleIdNotFoundException(IdNotFoundException ex) {
        log.warn("IdNotFoundException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
