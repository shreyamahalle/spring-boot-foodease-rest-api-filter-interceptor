package com.shreya.spring.model;

import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingTable {
    private Long id;
    private String customerName;
    private String restaurantName;
    private LocalDateTime bookingTime;
    private int numberOfPeople;
    private String status;

    public BookingTable(long id, String customerName, String restaurantName, Time bookingTime, int numberOfPeople, String status) {
    }
}
