package com.shreya.spring.service.impl;

import com.shreya.spring.exception.OrderNotFoundException;
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
        log.info("Adding order: {}", order);
        return orderRepository.addOrder(order);
    }

    @Override
    public List<Order> retrieveAllOrders() {
        log.info("Retrieving all orders");
        return orderRepository.retrieveOrders();
    }

    @Override
    public Order retrieveOrderByIdAndType(int id, String type) {
        log.info("Getting order by ID: {} and Type: {}", id, type);
        return orderRepository.retrieveOrder(id, type);
    }

    @Override
    public String updateOrder(Order order) throws SQLException {
        boolean updated = orderRepository.updateOrder(order.getId(), order.getType());
        if (!updated) {
            throw new OrderNotFoundException("Order not found with ID: " + order.getId());
        }
        return order.getType();
    }

    @Override
    public boolean deleteOrder(int id) throws SQLException {
        boolean deleted = orderRepository.deleteOrder(id);
        if (!deleted) {
            throw new OrderNotFoundException("Order not found with ID: " + id);
        }
        return true;
    }
}
