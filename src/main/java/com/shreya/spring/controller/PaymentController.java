package com.shreya.spring.controller;

import com.shreya.spring.exception.PaymentAlreadyExistsException;
import com.shreya.spring.exception.PaymentNotFoundException;
import com.shreya.spring.exception.PaymentServiceException;
import com.shreya.spring.model.Payment;
import com.shreya.spring.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/paymentManagement")
public class PaymentController {

    private final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<String> addPayment(@RequestBody Payment payment) {
        try {
            log.info("Adding payment: {}", payment);
            boolean status = paymentService.addPayment(payment);
            if (!status) throw new PaymentAlreadyExistsException("Payment already exists.");
            return ResponseEntity.status(HttpStatus.CREATED).body("Payment added successfully.");
        } catch (SQLException e) {
            throw new PaymentServiceException("Database error while adding payment.");
        }
    }

    @GetMapping("/payment")
    public ResponseEntity<List<Payment>> getAllPayments() {
        try {
            log.info("Fetching all payments");
            List<Payment> payments = paymentService.getAllPayments();
            return ResponseEntity.ok(payments);
        } catch (SQLException e) {
            throw new PaymentServiceException("Database error while fetching all payments.");
        }
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int id) {
        try {
            log.info("Fetching payment by ID: {}", id);
            Payment payment = paymentService.getPaymentById(id);
            if (payment == null) throw new PaymentNotFoundException("Payment not found for ID: " + id);
            return ResponseEntity.ok(payment);
        } catch (SQLException e) {
            throw new PaymentServiceException("Database error while fetching payment.");
        }
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<String> updatePayment(@PathVariable int id, @RequestBody Payment payment) {
        try {
            payment.setId((long) id);
            log.info("Updating payment ID: {} with data: {}", id, payment);
            boolean updated = paymentService.updatePayment(payment);
            if (!updated) throw new PaymentNotFoundException("Payment not found to update.");
            return ResponseEntity.ok("Payment updated successfully.");
        } catch (SQLException e) {
            throw new PaymentServiceException("Database error while updating payment.");
        }
    }

    @DeleteMapping("/payment/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable int id) {
        try {
            log.info("Deleting payment by ID: {}", id);
            boolean deleted = paymentService.deletePayment(id);
            if (!deleted) throw new PaymentNotFoundException("Payment not found to delete.");
            return ResponseEntity.ok("Payment deleted successfully.");
        } catch (SQLException e) {
            throw new PaymentServiceException("Database error while deleting payment.");
        }
    }
}
