package com.shreya.spring.controller;

import com.shreya.spring.exception.OrderNotFoundException;
import com.shreya.spring.model.Order;
import com.shreya.spring.service.EmailService;
import com.shreya.spring.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/orderManagement")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/order")
    public ResponseEntity<String> addOrder(@RequestBody Order order) throws SQLException {
        log.info("Placing new order: {}", order);

        boolean result = orderService.addOrder(order);
        if (result) {
            emailService.sendEmail(order.getCustomerEmail(), "Order Confirmation", "Your order has been placed!");
            return ResponseEntity.ok("Order placed and email sent!");
        } else {
            return ResponseEntity.status(500).body("Failed to place order");
        }
    }

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getAllOrders() {
        log.info("Fetching all orders");
        return ResponseEntity.ok(orderService.retrieveAllOrders());
    }

    @GetMapping("/order/{id}/{type}")
    public ResponseEntity<Order> getOrderByIdAndType(@PathVariable int id, @PathVariable String type) {
        log.info("Fetching order with ID: {} and Type: {}", id, type);
        Order order = orderService.retrieveOrderByIdAndType(id, type);
        if (order == null) {
            throw new OrderNotFoundException("No order found for ID: " + id + " and type: " + type);
        }
        return ResponseEntity.ok(order);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable int id, @RequestBody Order order) throws SQLException {
        log.info("Updating order ID: {}", id);
        order.setId(id);
        String updatedType = orderService.updateOrder(order);
        return ResponseEntity.ok("Order updated to type: " + updatedType);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) throws SQLException {
        log.info("Deleting order ID: {}", id);
        boolean deleted = orderService.deleteOrder(id);
        if (deleted) {
            return ResponseEntity.ok("Order deleted successfully");
        } else {
            throw new OrderNotFoundException("Order not found with ID: " + id);
        }
    }

    // Optional: handle HTML form
    @PostMapping("/form-order")
    public String submitOrder(@ModelAttribute Order order) throws SQLException {
        log.info("Submitting order from form: {}", order);
        orderService.addOrder(order);
        return "redirect:/order.html";
    }
}
