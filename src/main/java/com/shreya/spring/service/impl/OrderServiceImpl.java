package com.shreya.spring.service.impl;

import com.shreya.spring.model.Order;
import com.shreya.spring.repository.OrderRepository;
import com.shreya.spring.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service

public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean addOrder(Order order) throws SQLException {
        log.info("add Order  {}", order);
        return orderRepository.addOrder(order);
    }

    @Override
    public List<Order> retrieveAllOrders() {
        log.info("all Order");
        return orderRepository.retrieveOrders();
    }

    @Override
    public Order retrieveOrderByIdAndType(int id, String type) {
        log.info("Retrieving Order by ID: {} and Type: {}", id, type);
        return orderRepository.retrieveOrder(id, type);
    }

    @Override
    public String updateOrder(Order order) throws SQLException {
        int orderId = order.getId();
        String orderType = order.getType();

        log.info("Updating order with ID: {} to new type: {}", orderId, orderType);

        boolean updated = orderRepository.updateOrder(orderId, orderType);  // Update the order
        return orderType;
    }

    @Override
    public boolean deleteOrder(int id) throws SQLException {
        log.info("delete  Order by ID: {}", id);
        return orderRepository.deleteOrder(id);
    }

}