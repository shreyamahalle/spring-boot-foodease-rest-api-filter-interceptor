package com.shreya.spring.controller;

import com.shreya.spring.model.OrderStatus;
import com.shreya.spring.service.OrderStatusService;
import com.shreya.spring.exception.OrderStatusNotFoundException;
import com.shreya.spring.exception.DatabaseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/orderStatusManagement")
public class OrderStatusController {

    private final Logger log = LoggerFactory.getLogger(OrderStatusController.class);

    @Autowired
    private OrderStatusService orderStatusService;

    @PostMapping("/orderStatus")
    public ResponseEntity<Boolean> addOrderStatus(@RequestBody OrderStatus orderStatus) throws SQLException {
        log.info("Adding OrderStatus: {}", orderStatus);
        boolean result = orderStatusService.addOrderStatus(orderStatus);
        if (!result) {
            throw new DatabaseException("Failed to add OrderStatus");
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("/orderStatus")
    public ResponseEntity<List<OrderStatus>> getAllOrderStatus() throws SQLException {
        log.info("Fetching all OrderStatus entries");
        List<OrderStatus> list = orderStatusService.getAllOrderStatus();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/orderStatus/{id}")
    public ResponseEntity<OrderStatus> getOrderStatusById(@PathVariable long id) throws SQLException {
        log.info("Fetching OrderStatus with ID: {}", id);
        OrderStatus orderStatus = orderStatusService.getOrderStatusById(id);
        if (orderStatus == null) {
            throw new OrderStatusNotFoundException("OrderStatus not found with id: " + id);
        }
        return ResponseEntity.ok(orderStatus);
    }

    @PutMapping("/orderStatus/{id}")
    public ResponseEntity<Boolean> updateOrderStatus(@PathVariable long id, @RequestBody OrderStatus orderStatus) {
        orderStatus.setId(id);
        log.info("Updating OrderStatus with ID: {} | New Data: {}", id, orderStatus);
        boolean result = orderStatusService.updateOrderStatus(orderStatus);
        if (!result) {
            throw new DatabaseException("Failed to update OrderStatus with id: " + id);
        }
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/orderStatus/{id}")
    public ResponseEntity<Boolean> deleteOrderStatus(@PathVariable long id) {
        log.info("Deleting OrderStatus with ID: {}", id);
        boolean result = orderStatusService.deleteOrderStatus(id);
        if (!result) {
            throw new OrderStatusNotFoundException("Failed to delete OrderStatus with id: " + id);
        }
        return ResponseEntity.ok(true);
    }
}
