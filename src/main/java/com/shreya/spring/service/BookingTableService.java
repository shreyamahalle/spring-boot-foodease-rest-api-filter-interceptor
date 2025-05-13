package com.shreya.spring.service;

import com.shreya.spring.model.BookingTable;

import java.sql.SQLException;
import java.util.List;

public interface BookingTableService {

    boolean addBooking(BookingTable bookingTable) throws SQLException;

    List<BookingTable> getAllBookings();

    BookingTable getBookingById(Long id);

    boolean deleteBooking(Long id);

    boolean updateBooking(long id, BookingTable bookingTable);
}
