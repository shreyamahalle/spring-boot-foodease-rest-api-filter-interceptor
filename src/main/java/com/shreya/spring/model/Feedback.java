package com.shreya.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Feedback {

    private Long id;
    private Customer customer;
    private Order order;
    private int rating;
    private String comment;
    private String feedbackDate;

}
