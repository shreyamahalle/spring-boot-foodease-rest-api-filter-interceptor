package com.shreya.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    private int id;
    private String type;
    private String note;
    private String paymentMethod;

}
