package com.shreya.spring.controller;

import com.shreya.spring.model.DeliveryAgent;
import com.shreya.spring.service.DeliveryAgentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/deliveryAgentManagement")
@RequiredArgsConstructor
public class DeliveryAgentController {

    private final Logger log = LoggerFactory.getLogger(DeliveryAgentController.class);

    @Autowired
    private final DeliveryAgentService deliveryAgentService;

    @PostMapping("/deliveryAgent")
    public boolean addDeliveryAgent(@RequestBody DeliveryAgent deliveryAgent) throws SQLException {
        log.info("Received request to add delivery agent: {}", deliveryAgent);
        return deliveryAgentService.addDeliveryAgent(deliveryAgent);
    }

    @PutMapping("/deliveryAgent/{id}")
    public boolean updateDeliveryAgent(@PathVariable int id, @RequestBody DeliveryAgent deliveryAgent) throws SQLException {
        deliveryAgent.setId(id);
        log.info("Received request to update delivery agent with id: {}", id);
        return deliveryAgentService.updateDeliveryAgent(deliveryAgent);
    }

    @DeleteMapping("/deliveryAgent/{id}")
    public boolean deleteDeliveryAgent(@PathVariable int id) throws SQLException {
        log.info("Received request to delete delivery agent with id: {}", id);
        return deliveryAgentService.deleteDeliveryAgent(id);
    }

    @GetMapping("/deliveryAgent")
    public List<DeliveryAgent> getAllDeliveryAgents() throws SQLException {
        log.info("Received request to fetch all delivery agents");
        return deliveryAgentService.retrieveAllDeliveryAgents();
    }

    @GetMapping("/deliveryAgent/{id}")
    public DeliveryAgent getDeliveryAgentById(@PathVariable int id) throws SQLException {
        log.info("Received request to fetch delivery agent by id: {}", id);
        return deliveryAgentService.getDeliveryAgentById(id);
    }
}