package com.shreya.spring.service.impl;

import com.shreya.spring.model.BookingTable;
import com.shreya.spring.repository.BookingTableRepository;
import com.shreya.spring.service.BookingTableService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingTableServiceImpl implements BookingTableService {

    private final Logger log = LoggerFactory.getLogger(BookingTableServiceImpl.class);

    @Autowired
    private final BookingTableRepository bookingTableRepository;

    @Override
    public boolean addBooking(BookingTable bookingTable) throws SQLException {
        log.info("TableBooking added : {}", bookingTable);
        return bookingTableRepository.addBooking(bookingTable);
    }

    @Override
    public List<BookingTable> getAllBookings() {
        log.debug("Service: Fetching all bookings");
        List<BookingTable> bookings = bookingTableRepository.retrieveBookings();
        log.debug("Service: Retrieved {} bookings", bookings.size());
        return bookings;
    }

    @Override
    public BookingTable getBookingById(Long id) {
        log.info("get booking by id {}", id);
        return bookingTableRepository.findById(id);
    }

    @Override
    public boolean deleteBooking(Long id) {
        log.info("delete booking by id {}", id);
        return bookingTableRepository.deleteBooking(id);
    }

    @Override
    public boolean updateBooking(long id, BookingTable bookingTable) {
        log.info("update booking by id {} with data {}", id, bookingTable);
        return bookingTableRepository.updateBooking(bookingTable);
    }
}
