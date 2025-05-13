package com.shreya.spring.service.impl;

import com.shreya.spring.exception.CustomerNotfound;
import com.shreya.spring.model.Customer;
import com.shreya.spring.repository.CustomerRepository;
import com.shreya.spring.service.CustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private final CustomerRepository customerRepository;

    public String addCustomer(Customer customer) throws SQLException {
        log.info("Saving Customer{}", customer);
        customerRepository.addCustomer(customer);
        return null;
    }

    public boolean deleteCustomer(int id) throws SQLException {
        log.info("delete Customer{}", id);
        return customerRepository.deleteCustomer(id);
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        log.info("update Customer{}", customer);
        return customerRepository.updateCustomer(customer);
    }


    public List<Customer> retrieveCustomers() {
        log.info("all Customer");
        return customerRepository.retrieveCustomers();
    }

    public Customer getCustomerById(int id) throws CustomerNotfound {
        Customer customer = customerRepository.findById(id);
        log.info("Fetching customer by ID: {}", id);

        if (customer != null) {
            log.info("Customer found: {}", customer);
            return customer;

        } else {
            log.error("Customer not found with ID: {}", id);
            throw new CustomerNotfound("Customer not found with ID: " + id);
        }
    }

    public boolean updatePartialCustomer(Customer customer) throws SQLException {
        log.info("update Partial Customer: {}", customer);
        return customerRepository.updatePartialCustomer(customer);
    }
}
