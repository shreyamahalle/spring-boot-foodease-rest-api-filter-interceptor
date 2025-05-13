package com.shreya.spring.controller;

import com.shreya.spring.model.Payment;
import com.shreya.spring.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean addPayment(@RequestBody Payment payment) throws SQLException {
        log.info("Adding payment: {}", payment);
        return paymentService.addPayment(payment);
    }

    @GetMapping("/payment")
    public List<Payment> getAllPayments() throws SQLException {
        log.info("Fetching all payments");
        return paymentService.getAllPayments();
    }

    @GetMapping("/payment/{id}")
    public Payment getPaymentById(@PathVariable int id) throws SQLException {
        log.info("Fetching payment by ID: {}", id);
        return paymentService.getPaymentById(id);
    }

    @PutMapping("/payment/{id}")
    public boolean updatePayment(@PathVariable int id, @RequestBody Payment payment) throws SQLException {
        payment.setId((long) id);
        log.info("Updating payment ID: {} with data: {}", id, payment);
        return paymentService.updatePayment(payment);
    }

    @DeleteMapping("/payment/{id}")
    public boolean deletePayment(@PathVariable int id) throws SQLException {
        log.info("Deleting payment by ID: {}", id);
        return paymentService.deletePayment(id);
    }
}
