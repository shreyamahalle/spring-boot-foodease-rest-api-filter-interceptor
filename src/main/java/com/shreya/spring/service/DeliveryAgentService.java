package com.shreya.spring.service;

import com.shreya.spring.model.DeliveryAgent;

import java.sql.SQLException;
import java.util.List;

public interface DeliveryAgentService {

    boolean addDeliveryAgent(DeliveryAgent deliveryAgent) throws SQLException;

    boolean updateDeliveryAgent(DeliveryAgent deliveryAgent) throws SQLException;

    boolean deleteDeliveryAgent(int id) throws SQLException;

    DeliveryAgent getDeliveryAgentById(int id) throws SQLException;

    List<DeliveryAgent> retrieveAllDeliveryAgents() throws SQLException;
}
