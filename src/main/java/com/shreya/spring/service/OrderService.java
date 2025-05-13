package com.shreya.spring.service;

import com.shreya.spring.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    boolean addOrder(Order order) throws SQLException;

    List<Order> retrieveAllOrders();

    Order retrieveOrderByIdAndType(int id, String type);

    String updateOrder(Order order) throws SQLException;

    boolean deleteOrder(int id) throws SQLException;
}
