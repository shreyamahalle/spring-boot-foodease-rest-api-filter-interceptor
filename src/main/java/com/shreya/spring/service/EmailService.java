package com.shreya.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final MailSender mailSender;

    @Autowired
    public EmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String toEmail, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(toEmail);  // Receiver email
        email.setSubject("Order Reminder");  // Email subject
        email.setText(message);  // Email body

        mailSender.send(email);  // Send email
    }
}
