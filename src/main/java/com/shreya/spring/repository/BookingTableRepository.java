package com.shreya.spring.repository;

import com.shreya.spring.exception.CustomerNotfound;
import com.shreya.spring.model.BookingTable;
import com.shreya.spring.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingTableRepository {

    private final Logger log = LoggerFactory.getLogger(BookingTableRepository.class);

    public boolean addBooking(BookingTable bookingTable) {

        if (bookingTable.getCustomerName() == null || bookingTable.getCustomerName().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedBookingTime = bookingTable.getBookingTime().format(formatter);

        String query = "INSERT INTO booking_table (customer_name, restaurant_name, booking_time, number_of_people, status) VALUES (?, ?, ?, ?, ?)";
        log.info("Creating booking: {}", bookingTable);

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, bookingTable.getCustomerName());
            ps.setString(2, bookingTable.getRestaurantName());
            ps.setString(3, formattedBookingTime);
            ps.setInt(4, bookingTable.getNumberOfPeople());
            ps.setString(5, bookingTable.getStatus());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            log.error("Error creating booking: {}", bookingTable, e);
            throw new RuntimeException("Error creating booking", e);
        }
    }

    public List<BookingTable> retrieveBookings() {
        List<BookingTable> bookings = new ArrayList<>();
        String query = "SELECT * FROM booking_table";
        log.info("Retrieving all bookings");

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BookingTable booking = new BookingTable(
                        rs.getLong("id"),
                        rs.getString("customer_name"),
                        rs.getString("restaurant_name"),
                        rs.getTimestamp("booking_time").toLocalDateTime(),
                        rs.getInt("number_of_people"),
                        rs.getString("status")
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            log.error("Error retrieving bookings", e);
            throw new RuntimeException("Error retrieving bookings", e);
        }
        return bookings;
    }

    public BookingTable findById(Long id) {
        String query = "SELECT * FROM booking_table WHERE id = ?";
        log.info("Finding booking by id: {}", id);

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new BookingTable(
                            rs.getLong("id"),
                            rs.getString("customer_name"),
                            rs.getString("restaurant_name"),
                            rs.getTimestamp("booking_time").toLocalDateTime(),
                            rs.getInt("number_of_people"),
                            rs.getString("status")
                    );
                } else {
                    throw new CustomerNotfound("Booking with ID " + id + " not found.");
                }
            } catch (CustomerNotfound e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            log.error("Error finding booking by id: {}", id, e);
            throw new RuntimeException("Error finding booking", e);
        }
    }

    public boolean deleteBooking(Long id) {
        String query = "DELETE FROM booking_table WHERE id = ?";
        log.info("Deleting booking with id: {}", id);

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error deleting booking with id: {}", id, e);
            return false;
        }
    }

    public boolean updateBooking(BookingTable bookingTable) {
        String query = "UPDATE booking_table SET customer_name = ?, restaurant_name = ?, booking_time = ?, number_of_people = ?, status = ? WHERE id = ?";
        log.info("Updating booking: {}", bookingTable);

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, bookingTable.getCustomerName());
            ps.setString(2, bookingTable.getRestaurantName());
            ps.setTimestamp(3, Timestamp.valueOf(bookingTable.getBookingTime()));
            ps.setInt(4, bookingTable.getNumberOfPeople());
            ps.setString(5, bookingTable.getStatus());
            ps.setLong(6, bookingTable.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error updating booking: {}", bookingTable, e);
            return false;
        }
    }
}
