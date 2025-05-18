package com.shreya.spring.service;

import com.shreya.spring.exception.CustomerNotFoundException;
import com.shreya.spring.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {

    String addCustomer(Customer customer) throws SQLException;

    boolean deleteCustomer(int id) throws SQLException;

    boolean updateCustomer(Customer customer) throws SQLException;

    List<Customer> retrieveCustomers();

    Customer getCustomerById(int id) throws CustomerNotFoundException;

    boolean updatePartialCustomer(Customer customer) throws SQLException;

}
