package com.shreya.spring.service.impl;

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
    public boolean addOrderStatus(OrderStatus orderStatus) throws SQLException {
        log.info("add Order Status {}", orderStatus);
        return orderStatusRepository.addorderStatus(orderStatus);
    }

    @Override
    public List<OrderStatus> getAllOrderStatus() throws SQLException {
        log.info("all Order Status");
        return orderStatusRepository.retrieveOrderStatuses();
    }

    @Override
    public OrderStatus getOrderStatusById(Long id) throws SQLException {
        log.info("get Order Status by id{}", id);
        return orderStatusRepository.retrieveOrderStatus(id);
    }

    @Override
    public boolean updateOrderStatus(OrderStatus orderStatus) {
        log.info("update Order Status {}", orderStatus);
        return orderStatusRepository.updateOrderStatus(orderStatus);
    }

    @Override
    public boolean deleteOrderStatus(Long id) {
        log.info("delete Order Status {}", id);
        return orderStatusRepository.deleteOrderStatus(id);
    }
}
