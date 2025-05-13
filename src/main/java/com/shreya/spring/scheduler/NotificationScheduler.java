package com.shreya.spring.scheduler;

import com.shreya.spring.model.Order;
import com.shreya.spring.service.EmailService;
import com.shreya.spring.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationScheduler {

    private static final Logger logger = LoggerFactory.getLogger(NotificationScheduler.class);

    @Autowired
    private OrderService orderService;  // Injecting the OrderService to access order-related logic

    @Autowired
    private EmailService emailService;  // Injecting the EmailService to send emails

    @Scheduled(fixedRate = 60000)  // Runs every 60 seconds
    public void sendReminders() {
        logger.info("⏰ Running scheduled task to check for pending orders...");

        try {
            // Retrieve all orders that are in the "PENDING" status
            List<Order> pendingOrders = orderService.retrieveAllOrders();

            if (pendingOrders.isEmpty()) {
                logger.info("📋 No pending orders to process.");
                return;
            }

            // Loop through the pending orders and send reminders (or notifications)
            for (Order order : pendingOrders) {
                if ("PENDING".equals(order.getType())) {  // Check if the order status is "PENDING"
                    // Retrieve the customer email (assuming Order has a customer email field or you fetch it separately)
                    String customerEmail = "customer@example.com";  // Replace with actual customer email field or fetch logic

                    // Sending the reminder email
                    String message = "Reminder: Your order (ID: " + order.getId() + ") is still pending. Please take action!";
                    logger.info("📧 Sending reminder email to customer: {}", customerEmail);
                    emailService.sendEmail(customerEmail, message);
                }
            }

        } catch (Exception e) {
            logger.error("🚨 Error occurred while processing pending orders: {}", e.getMessage());
        }
    }
}
