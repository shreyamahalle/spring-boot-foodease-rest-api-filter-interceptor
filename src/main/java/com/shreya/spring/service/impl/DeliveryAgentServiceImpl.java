package com.shreya.spring.service.impl;

import com.shreya.spring.model.DeliveryAgent;
import com.shreya.spring.repository.DeliveryAgentRepository;
import com.shreya.spring.service.DeliveryAgentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor

public class DeliveryAgentServiceImpl implements DeliveryAgentService {

    private final Logger log = LoggerFactory.getLogger(DeliveryAgentServiceImpl.class);

    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;

    @Override
    public boolean addDeliveryAgent(DeliveryAgent deliveryAgent) throws SQLException {
        log.info("Saving DeliveryAgent{}", deliveryAgent);
        return deliveryAgentRepository.addDeliveryAgent(deliveryAgent);
    }

    @Override
    public boolean updateDeliveryAgent(DeliveryAgent deliveryAgent) throws SQLException {
        log.info("update DeliveryAgent{}", deliveryAgent);
        return deliveryAgentRepository.updateDeliveryAgent(deliveryAgent);
    }

    @Override
    public boolean deleteDeliveryAgent(int id) throws SQLException {
        log.info("delete DeliveryAgent{}", id);
        return deliveryAgentRepository.deleteDeliveryAgent(id);
    }

    @Override
    public DeliveryAgent getDeliveryAgentById(int id) throws SQLException {
        log.info("get DeliveryAgent by id{}", id);
        return deliveryAgentRepository.findById(id);
    }

    @Override
    public List<DeliveryAgent> retrieveAllDeliveryAgents() throws SQLException {
        log.info("all DeliveryAgent");
        return deliveryAgentRepository.retrieveDeliveryAgents();
    }
}