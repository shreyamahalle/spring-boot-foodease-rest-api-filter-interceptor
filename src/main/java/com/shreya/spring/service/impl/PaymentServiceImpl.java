package com.shreya.spring.service.impl;

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
        log.info("add Payment {}", payment);
        return paymentRepository.addPayment(payment);
    }

    @Override
    public List<Payment> getAllPayments() throws SQLException {
        log.info("get All Payments");
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(int id) throws SQLException {
        log.info("get Payments Id {}", id);
        return paymentRepository.findById(id);
    }

    @Override
    public boolean updatePayment(Payment payment) throws SQLException {
        log.info("update Payments {}", payment);
        return paymentRepository.update(payment);
    }

    @Override
    public boolean deletePayment(int id) throws SQLException {
        log.info("delete payment {}", id);
        return paymentRepository.delete(id);
    }
}
