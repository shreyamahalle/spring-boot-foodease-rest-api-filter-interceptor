package com.shreya.spring.service;

import com.shreya.spring.exception.CustomerNotfound;
import com.shreya.spring.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {

    String addCustomer(Customer customer) throws SQLException;

    boolean deleteCustomer(int id) throws SQLException;

    boolean updateCustomer(Customer customer) throws SQLException;

    List<Customer> retrieveCustomers();

    Customer getCustomerById(int id) throws CustomerNotfound;

    boolean updatePartialCustomer(Customer customer) throws SQLException;

}
