package com.shreya.spring.controller;

import com.shreya.spring.exception.CustomerNotfound;
import com.shreya.spring.model.Customer;
import com.shreya.spring.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/customerManagement")  // Base URL: /customers
public class CustomerController {

    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public String addCustomer(@RequestBody Customer customer) throws SQLException {
        log.info("Received request to add customer: {}", customer);
        return customerService.addCustomer(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        log.info("Received request to fetch all customers");
        return customerService.retrieveCustomers();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable int id) throws CustomerNotfound {
        log.info("Received request to fetch customer by id: {}", id);
        return customerService.getCustomerById(id);
    }

    @PutMapping("/customer/{id}")
    public boolean updateCustomer(@PathVariable int id, @RequestBody Customer customer) throws SQLException {
        log.info("Received request to update customer with id: {}", id);
        customer.setId(id);
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")
    public boolean deleteCustomer(@PathVariable int id) throws SQLException {
        log.info("Received request to delete customer with id: {}", id);
        return customerService.deleteCustomer(id);
    }

    @PatchMapping("/customer/{id}")
    public boolean updatePartialCustomer(@PathVariable("id") int id, @RequestBody Customer customer) throws SQLException {
        log.info("Received request for partial update of customer with id: {}", id);
        return customerService.updatePartialCustomer(customer);
    }
}