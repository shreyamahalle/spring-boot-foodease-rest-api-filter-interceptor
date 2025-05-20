package com.shreya.spring.service.impl;

import com.shreya.spring.exception.PaymentAlreadyExistsException;
import com.shreya.spring.exception.PaymentNotFoundException;
import com.shreya.spring.exception.PaymentServiceException;
import com.shreya.spring.model.Payment;
import com.shreya.spring.repository.PaymentRepository;
import com.shreya.spring.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public boolean addPayment(Payment payment) throws SQLException {
        log.info("Add Payment: {}", payment);
        try {
            boolean exists = paymentRepository.findById(payment.getId().intValue()) != null;
            if (exists) {
                throw new PaymentAlreadyExistsException("Payment already exists with ID: " + payment.getId());
            }
            return paymentRepository.addPayment(payment);
        } catch (SQLException e) {
            throw new PaymentServiceException("Failed to add payment: " + e.getMessage());
        }
    }

    @Override
    public List<Payment> getAllPayments() throws SQLException {
        log.info("Get All Payments");
        try {
            return paymentRepository.findAll();
        } catch (SQLException e) {
            throw new PaymentServiceException("Failed to fetch payments: " + e.getMessage());
        }
    }

    @Override
    public Payment getPaymentById(int id) throws SQLException {
        log.info("Get Payment By ID: {}", id);
        try {
            Payment payment = paymentRepository.findById(id);
            if (payment == null) {
                throw new PaymentNotFoundException("Payment not found with ID: " + id);
            }
            return payment;
        } catch (SQLException e) {
            throw new PaymentServiceException("Failed to fetch payment: " + e.getMessage());
        }
    }

    @Override
    public boolean updatePayment(Payment payment) throws SQLException {
        log.info("Update Payment: {}", payment);
        try {
            boolean updated = paymentRepository.update(payment);
            if (!updated) {
                throw new PaymentNotFoundException("Cannot update. Payment not found with ID: " + payment.getId());
            }
            return true;
        } catch (SQLException e) {
            throw new PaymentServiceException("Failed to update payment: " + e.getMessage());
        }
    }

    @Override
    public boolean deletePayment(int id) throws SQLException {
        log.info("Delete Payment ID: {}", id);
        try {
            boolean deleted = paymentRepository.delete(id);
            if (!deleted) {
                throw new PaymentNotFoundException("Cannot delete. Payment not found with ID: " + id);
            }
            return true;
        } catch (SQLException e) {
            throw new PaymentServiceException("Failed to delete payment: " + e.getMessage());
        }
    }
}
