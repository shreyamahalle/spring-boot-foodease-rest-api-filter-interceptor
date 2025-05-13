package com.shreya.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DeliveryAgent {
    public int mobileNo;
    private int id;
    private String name;
    private String city;

    public DeliveryAgent(int id, String name, String city, int mobileNo) {
    }
}
