package com.shreya.spring.service.impl;

import com.shreya.spring.exception.DeliveryAgentDeleteException;
import com.shreya.spring.exception.DeliveryAgentNotFoundException;
import com.shreya.spring.exception.DeliveryAgentUpdateException;
import com.shreya.spring.model.DeliveryAgent;
import com.shreya.spring.repository.DeliveryAgentRepository;
import com.shreya.spring.service.DeliveryAgentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryAgentServiceImpl implements DeliveryAgentService {

    private final Logger log = LoggerFactory.getLogger(DeliveryAgentServiceImpl.class);
    private final DeliveryAgentRepository deliveryAgentRepository;

    @Override
    public boolean addDeliveryAgent(DeliveryAgent deliveryAgent) throws SQLException {
        log.info("Saving DeliveryAgent: {}", deliveryAgent);
        boolean isAdded = deliveryAgentRepository.addDeliveryAgent(deliveryAgent);
        if (!isAdded) {
            throw new DeliveryAgentNotFoundException("Failed to add delivery agent.");
        }
        return true;
    }

    @Override
    public boolean updateDeliveryAgent(DeliveryAgent deliveryAgent) throws SQLException {
        log.info("Updating DeliveryAgent: {}", deliveryAgent);
        boolean isUpdated = deliveryAgentRepository.updateDeliveryAgent(deliveryAgent);
        if (!isUpdated) {
            throw new DeliveryAgentUpdateException("Failed to update delivery agent with ID: " + deliveryAgent.getId());
        }
        return true;
    }

    @Override
    public boolean deleteDeliveryAgent(int id) throws SQLException {
        log.info("Deleting DeliveryAgent with ID: {}", id);
        boolean isDeleted = deliveryAgentRepository.deleteDeliveryAgent(id);
        if (!isDeleted) {
            throw new DeliveryAgentDeleteException("Failed to delete delivery agent with ID: " + id);
        }
        return true;
    }

    @Override
    public DeliveryAgent getDeliveryAgentById(int id) throws SQLException {
        log.info("Fetching DeliveryAgent by ID: {}", id);
        DeliveryAgent agent = deliveryAgentRepository.findById(id);
        if (agent == null) {
            throw new DeliveryAgentNotFoundException("Delivery agent not found with ID: " + id);
        }
        return agent;
    }

    @Override
    public List<DeliveryAgent> retrieveAllDeliveryAgents() throws SQLException {
        log.info("Fetching all DeliveryAgents");
        return deliveryAgentRepository.retrieveDeliveryAgents();
    }
}
