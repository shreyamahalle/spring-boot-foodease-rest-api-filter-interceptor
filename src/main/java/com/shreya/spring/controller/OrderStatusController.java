package com.shreya.spring.controller;

import com.shreya.spring.model.OrderStatus;
import com.shreya.spring.service.OrderStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean addOrderStatus(@RequestBody OrderStatus orderStatus) throws SQLException {
        log.info("Adding OrderStatus: {}", orderStatus);
        return orderStatusService.addOrderStatus(orderStatus);
    }

    @GetMapping("/orderStatus")
    public List<OrderStatus> getAllOrderStatus() throws SQLException {
        log.info("Fetching all OrderStatus entries");
        return orderStatusService.getAllOrderStatus();
    }

    @GetMapping("/orderStatus/{id}")
    public OrderStatus getOrderStatusById(@PathVariable long id) throws SQLException {
        log.info("Fetching OrderStatus with ID: {}", id);
        return orderStatusService.getOrderStatusById(id);
    }

    @PutMapping("/orderStatus/{id}")
    public boolean updateOrderStatus(@PathVariable long id, OrderStatus orderStatus) {
        orderStatus.setId(id);
        log.info("Updating OrderStatus with ID: {} | New Data: {}", id, orderStatus);
        return orderStatusService.updateOrderStatus(orderStatus);
    }

    @DeleteMapping("/orderStatus/{id}")
    public boolean deleteOderStatus(@PathVariable long id) {
        log.info("Deleting OrderStatus with ID: {}", id);
        return orderStatusService.deleteOrderStatus(id);
    }
}
