package com.shreya.spring.controller;

import com.shreya.spring.model.Order;
import com.shreya.spring.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/api/orderManagement")

public class OrderController {

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public boolean addOrder(@RequestBody Order order) throws SQLException {
        log.info("Adding new order: {}", order);
        return orderService.addOrder(order);
    }

    @GetMapping("/order")
    public List<Order> getAllOrders() {
        log.info("Fetching all orders");
        return orderService.retrieveAllOrders();
    }

    @GetMapping("/order/{id}/{type}")
    public Order getOrderIdAndType(@PathVariable int id, @PathVariable String type) {
        log.info("Fetching order with ID: {} and Type: {}", id, type);
        return orderService.retrieveOrderByIdAndType(id, type);
    }

    @PutMapping("/order/{id}")
    public String updateOrder(@PathVariable int id, @RequestBody Order order) throws SQLException {
        order.setId(id);
        log.info("Updating order with ID: {} with data: {}", id, order);
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/order/{id}")
    public boolean deleteOrder(@PathVariable int id) throws SQLException {
        log.info("Deleting order with ID: {}", id);
        return orderService.deleteOrder(id);
    }
}
