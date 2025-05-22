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
    private OrderService orderService;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 60000)  // Runs every 60 seconds
    public void sendReminders() {
        logger.info("Scheduler running: Checking for pending orders...");

        try {
            List<Order> allOrders = orderService.retrieveAllOrders();

            if (allOrders == null || allOrders.isEmpty()) {
                logger.info("No orders found.");
                return;
            }

            for (Order order : allOrders) {
                if ("PENDING".equalsIgnoreCase(order.getType())) {
                    String customerEmail = order.getCustomerEmail(); // Get from order object

                    if (customerEmail != null && !customerEmail.isEmpty()) {
                        String message = "Reminder: Your order (ID: " + order.getId() + ") is still pending. Please take action!";
                        emailService.sendEmail(customerEmail, message, "Test email content.");
                        logger.info("Reminder sent to: {}", customerEmail);
                    } else {
                        logger.warn("Skipped sending email for order ID {} due to missing email.", order.getId());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error during scheduler task: ", e);
        }
    }
}
