package com.shreya.spring.service.impl;

import com.shreya.spring.exception.DatabaseException;
import com.shreya.spring.exception.OrderStatusNotFoundException;
import com.shreya.spring.model.OrderStatus;
import com.shreya.spring.repository.OrderStatusRepository;
import com.shreya.spring.service.OrderStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private final Logger log = LoggerFactory.getLogger(OrderStatusServiceImpl.class);

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    public boolean addOrderStatus(OrderStatus orderStatus) {
        try {
            log.info("Adding Order Status: {}", orderStatus);
            return orderStatusRepository.addorderStatus(orderStatus);
        } catch (SQLException e) {
            log.error("Error while adding order status", e);
            throw new DatabaseException("Failed to add order status");
        }
    }

    @Override
    public List<OrderStatus> getAllOrderStatus() {
        try {
            log.info("Fetching all Order Statuses");
            return orderStatusRepository.retrieveOrderStatuses();
        } catch (SQLException e) {
            log.error("Error while fetching order statuses", e);
            throw new DatabaseException("Failed to fetch order statuses");
        }
    }

    @Override
    public OrderStatus getOrderStatusById(Long id) {
        try {
            log.info("Fetching Order Status by ID: {}", id);
            OrderStatus status = orderStatusRepository.retrieveOrderStatus(id);
            if (status == null) {
                throw new OrderStatusNotFoundException("OrderStatus with ID " + id + " not found");
            }
            return status;
        } catch (SQLException e) {
            log.error("Error while fetching order status by ID", e);
            throw new DatabaseException("Failed to fetch order status");
        }
    }

    @Override
    public boolean updateOrderStatus(OrderStatus orderStatus) {
        try {
            log.info("Updating Order Status: {}", orderStatus);
            boolean updated = orderStatusRepository.updateOrderStatus(orderStatus);
            if (!updated) {
                throw new OrderStatusNotFoundException("OrderStatus with ID " + orderStatus.getId() + " not found");
            }
            return true;
        } catch (SQLException e) {
            log.error("Error while updating order status", e);
            throw new DatabaseException("Failed to update order status");
        }
    }

    @Override
    public boolean deleteOrderStatus(Long id) {
        try {
            log.info("Deleting Order Status with ID: {}", id);
            boolean deleted = orderStatusRepository.deleteOrderStatus(id);
            if (!deleted) {
                throw new OrderStatusNotFoundException("OrderStatus with ID " + id + " not found");
            }
            return true;
        } catch (SQLException e) {
            log.error("Error while deleting order status", e);
            throw new DatabaseException("Failed to delete order status");
        }
    }
}
