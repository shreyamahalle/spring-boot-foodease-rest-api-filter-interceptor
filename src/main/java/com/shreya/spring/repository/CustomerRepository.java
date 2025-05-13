package com.shreya.spring.repository;

import com.shreya.spring.model.Customer;
import com.shreya.spring.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {

    private final Logger log = LoggerFactory.getLogger(CustomerRepository.class);

    public boolean addCustomer(Customer customer) {
        String query = "INSERT INTO customer (id, name, city, mobileNo, age) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, customer.getId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getCity());
            ps.setInt(4, customer.getMobileNo());
            ps.setInt(5, customer.getAge());

            int rowsAffected = ps.executeUpdate();
            log.info("Added customer: {}", customer);
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error adding customer: {}", customer, e);
            throw new RuntimeException("Error adding customer", e);
        }
    }

    public List<Customer> retrieveCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getInt("mobileNo"),
                        rs.getInt("age")
                );
                customers.add(customer);
            }
            log.info("Retrieved {} customers", customers.size());
        } catch (SQLException e) {
            log.error("Error retrieving customers", e);
            throw new RuntimeException("Error retrieving customers", e);
        }
        return customers;
    }

    public Customer findById(int id) {
        String query = "SELECT * FROM customer WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("city"),
                            rs.getInt("mobileNo"),
                            rs.getInt("age")
                    );
                    log.info("Found customer with id: {}", id);
                    return customer;
                }
            }
        } catch (SQLException e) {
            log.error("Error finding customer with id: {}", id, e);
        }
        return null; // Return null if customer not found
    }

    public boolean deleteCustomer(int id) {
        String query = "DELETE FROM customer WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            log.info("Deleted customer with id: {}", id);
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error deleting customer with id: {}", id, e);
            throw new RuntimeException("Error deleting customer", e);
        }
    }

    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE customer SET name = ?, city = ?, mobileNo = ?, age = ? WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getCity());
            ps.setInt(3, customer.getMobileNo());
            ps.setInt(4, customer.getAge());
            ps.setInt(5, customer.getId());

            int rowsAffected = ps.executeUpdate();
            log.info("Updated customer with id: {}", customer.getId());
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error updating customer with id: {}", customer.getId(), e);
            throw new RuntimeException("Error updating customer", e);
        }
    }

    public boolean updatePartialCustomer(Customer customer) {
        String query = "UPDATE customer SET name = ?, city = ?, mobileNo = ?, age = ? WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getCity());
            ps.setInt(3, customer.getMobileNo());
            ps.setInt(4, customer.getAge());
            ps.setInt(5, customer.getId());

            int rowsAffected = ps.executeUpdate();
            log.info("Partially updated customer with id: {}", customer.getId());
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error partially updating customer with id: {}", customer.getId(), e);
            throw new RuntimeException("Error partially updating customer", e);
        }
    }
}
