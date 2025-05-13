package com.shreya.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Review {
    private Long id;
    private int rating;
    private String comment;
    private Date reviewDate;
    private Restaurant restaurant;
    private Customer customer;
}