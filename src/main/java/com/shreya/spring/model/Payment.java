package com.shreya.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Payment {
    private Long id;
    private Order order;
    private Double amount;
    private String paymentMethod;
    private String paymentStatus;
    private String transactionId;
}
