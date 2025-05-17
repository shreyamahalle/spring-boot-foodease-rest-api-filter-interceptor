package com.shreya.spring.controller;

import com.shreya.spring.exception.DeliveryAgentDeleteException;
import com.shreya.spring.exception.DeliveryAgentNotFoundException;
import com.shreya.spring.exception.DeliveryAgentUpdateException;
import com.shreya.spring.model.DeliveryAgent;
import com.shreya.spring.service.DeliveryAgentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/deliveryAgentManagement")
@RequiredArgsConstructor
public class DeliveryAgentController {

    private final Logger log = LoggerFactory.getLogger(DeliveryAgentController.class);
    private final DeliveryAgentService deliveryAgentService;

    @PostMapping("/deliveryAgent")
    public ResponseEntity<String> addDeliveryAgent(@RequestBody DeliveryAgent deliveryAgent) {
        try {
            log.info("Received request to add delivery agent: {}", deliveryAgent);
            deliveryAgentService.addDeliveryAgent(deliveryAgent);
            return ResponseEntity.ok("Delivery agent added successfully");
        } catch (DeliveryAgentNotFoundException | SQLException e) {
            log.error("Failed to add delivery agent: {}", e.getMessage());
            return ResponseEntity.status(500).body("Failed to add delivery agent: " + e.getMessage());
        }
    }

    @PutMapping("/deliveryAgent/{id}")
    public ResponseEntity<String> updateDeliveryAgent(@PathVariable int id, @RequestBody DeliveryAgent deliveryAgent) {
        try {
            deliveryAgent.setId(id);
            log.info("Received request to update delivery agent with ID: {}", id);
            deliveryAgentService.updateDeliveryAgent(deliveryAgent);
            return ResponseEntity.ok("Delivery agent updated successfully");
        } catch (DeliveryAgentUpdateException | SQLException e) {
            log.error("Failed to update delivery agent: {}", e.getMessage());
            return ResponseEntity.status(500).body("Failed to update delivery agent: " + e.getMessage());
        }
    }

    @DeleteMapping("/deliveryAgent/{id}")
    public ResponseEntity<String> deleteDeliveryAgent(@PathVariable int id) {
        try {
            log.info("Received request to delete delivery agent with ID: {}", id);
            deliveryAgentService.deleteDeliveryAgent(id);
            return ResponseEntity.ok("Delivery agent deleted successfully");
        } catch (DeliveryAgentDeleteException | SQLException e) {
            log.error("Failed to delete delivery agent: {}", e.getMessage());
            return ResponseEntity.status(500).body("Failed to delete delivery agent: " + e.getMessage());
        }
    }

    @GetMapping("/deliveryAgent")
    public ResponseEntity<?> getAllDeliveryAgents() {
        try {
            log.info("Received request to fetch all delivery agents");
            List<DeliveryAgent> agents = deliveryAgentService.retrieveAllDeliveryAgents();
            return ResponseEntity.ok(agents);
        } catch (SQLException e) {
            log.error("Error fetching delivery agents: {}", e.getMessage());
            return ResponseEntity.status(500).body("Failed to fetch delivery agents");
        }
    }

    @GetMapping("/deliveryAgent/{id}")
    public ResponseEntity<?> getDeliveryAgentById(@PathVariable int id) {
        try {
            log.info("Received request to fetch delivery agent by ID: {}", id);
            DeliveryAgent agent = deliveryAgentService.getDeliveryAgentById(id);
            return ResponseEntity.ok(agent);
        } catch (DeliveryAgentNotFoundException | SQLException e) {
            log.error("Delivery agent not found: {}", e.getMessage());
            return ResponseEntity.status(404).body("Delivery agent not found: " + e.getMessage());
        }
    }
}
