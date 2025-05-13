package com.shreya.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {
    private Long id;
    private String code;
    private String description;
    private Double discountAmount;
    private boolean active;
}
