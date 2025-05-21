package com.shreya.spring.controller;

import com.shreya.spring.exception.IdNotFoundException;
import com.shreya.spring.exception.NoBookingTablesFoundException;
import com.shreya.spring.exception.ResourceNotFoundException;
import com.shreya.spring.model.BookingTable;
import com.shreya.spring.service.BookingTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/tableBookingManagement")
public class BookingTableController {

    private static final Logger log = LoggerFactory.getLogger(BookingTableController.class);

    @Autowired
    private BookingTableService bookingTableService;

    @PostMapping("/table")
    public ResponseEntity<String> addBooking(@RequestBody BookingTable bookingTable) throws SQLException {
        log.debug("Received request to add booking: {}", bookingTable);
        bookingTableService.addBooking(bookingTable);  // throws BookingNotAddedException if fails
        log.debug("Booking added successfully");
        return ResponseEntity.status(HttpStatus.OK).body("Booking added successfully.");
    }

    @GetMapping("/table")
    public ResponseEntity<List<BookingTable>> getAllBookings() {
        log.info("Entered getAllBookings()");
        List<BookingTable> bookings = bookingTableService.getAllBookings();

        if (bookings == null || bookings.isEmpty()) {
            log.warn("No booking tables found.");
            throw new NoBookingTablesFoundException();
        }
        log.debug("Fetched {} bookings", bookings.size());
        return new ResponseEntity<>(bookingTableService.getAllBookings(),HttpStatus.OK);
    }

    @GetMapping("/table/{id}")
    public ResponseEntity<BookingTable> getBookingById(@PathVariable Long id) {
        log.info("Fetching booking by ID: {}", id);
        BookingTable booking = bookingTableService.getBookingById(id);
        if (booking == null) {
            throw new ResourceNotFoundException("Booking not found with id: " + id);
        }
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/table/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable Long id, @RequestBody BookingTable bookingTable) {
        log.info("Updating booking with ID: {}. Data: {}", id, bookingTable);
        boolean updated = bookingTableService.updateBooking(id, bookingTable);
        if (updated) {
            return ResponseEntity.ok("Booking updated successfully.");
        } else {
            throw new ResourceNotFoundException("Booking not found with id: " + id);
        }
    }

    @DeleteMapping("/table/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        log.info("Deleting booking with ID: {}", id);
        boolean deleted = bookingTableService.deleteBooking(id);
        if (deleted) {
            return ResponseEntity.ok("Booking deleted successfully.");
        } else {
            throw new IdNotFoundException("Booking not found with id: " + id);
        }
    }
}
