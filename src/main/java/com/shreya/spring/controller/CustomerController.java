package com.shreya.spring.controller;

import com.shreya.spring.exception.CustomerNotFoundException;
import com.shreya.spring.exception.CustomerServiceException;
import com.shreya.spring.model.Customer;
import com.shreya.spring.service.CustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customerManagement")
public class CustomerController {


    private final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private final CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        try {
            log.info("Received request to add customer: {}", customer);
            String message = customerService.addCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            log.error("Error while adding customer", e);
            throw new CustomerServiceException("Failed to add customer", e);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            log.info("Received request to fetch all customers");
            List<Customer> customers = customerService.retrieveCustomers();
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            log.error("Error fetching customers", e);
            throw new CustomerServiceException("Unable to fetch customers", e);
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        try {
            log.info("Received request to fetch customer by id: {}", id);
            Customer customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (CustomerNotFoundException e) {
            log.warn("Customer not found: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Error fetching customer by id", e);
            throw new CustomerServiceException("Failed to fetch customer", e);
        }
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        try {
            log.info("Received request to update customer with id: {}", id);
            customer.setId(id);
            boolean updated = customerService.updateCustomer(customer);
            return updated ? ResponseEntity.ok("Customer updated successfully")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } catch (Exception e) {
            log.error("Error updating customer", e);
            throw new CustomerServiceException("Failed to update customer", e);
        }
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        try {
            log.info("Received request to delete customer with id: {}", id);
            boolean deleted = customerService.deleteCustomer(id);
            return deleted ? ResponseEntity.ok("Customer deleted successfully")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } catch (Exception e) {
            log.error("Error deleting customer", e);
            throw new CustomerServiceException("Failed to delete customer", e);
        }
    }

    @PatchMapping("/customer/{id}")
    public ResponseEntity<String> updatePartialCustomer(@PathVariable int id, @RequestBody Customer customer) {
        try {
            log.info("Received request for partial update of customer with id: {}", id);
            customer.setId(id);
            boolean updated = customerService.updatePartialCustomer(customer);
            return updated ? ResponseEntity.ok("Customer partially updated")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } catch (Exception e) {
            log.error("Error in partial update", e);
            throw new CustomerServiceException("Failed to update customer partially", e);
        }
    }
}
