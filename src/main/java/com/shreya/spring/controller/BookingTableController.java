package com.shreya.spring.controller;

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

    private final Logger log = LoggerFactory.getLogger(BookingTableController.class);

    @Autowired
    private BookingTableService bookingTableService;

    @PostMapping("/table")
    public boolean addBooking(@RequestBody BookingTable bookingTable) throws SQLException {
        log.debug("Received request to add booking: {}", bookingTable);
        boolean response =  bookingTableService.addBooking(bookingTable);
        log.debug("Booking added successfully: {}", response);
        return response;
    }

    @GetMapping("/table")
    public ResponseEntity<List<BookingTable>>getAllBookings() {
        log.debug("Entered getAllBookings()");
        List<BookingTable> bookings = bookingTableService.getAllBookings();
        log.debug("Fetched {} bookings", bookings.size());
        return new ResponseEntity<List<BookingTable>>(bookings, HttpStatus.OK);
    }

    @GetMapping("/table/{id}")
    public BookingTable getBookingById(@PathVariable("id") Long id) {
        log.info("Received request to fetch booking by ID: {}", id);
        return bookingTableService.getBookingById(id);
    }

    @PutMapping("/table/{id}")
    public boolean updateBooking(@PathVariable Long id, @RequestBody BookingTable bookingTable) {
        log.info("Received request to update booking with ID: {}. New data: {}", id, bookingTable);
        return bookingTableService.updateBooking(id, bookingTable);
    }

    @DeleteMapping("/table/{id}")
    public boolean deleteBooking(@PathVariable Long id) {
        log.info("Received request to delete booking with ID: {}", id);
        return bookingTableService.deleteBooking(id);
    }
}