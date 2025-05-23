package com.shreya.spring;

import com.shreya.spring.exception.InvalideCustomerIDException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.SQLException;

@SpringBootApplication
@EnableScheduling
public class SpringBootBookingManagementProjectApplication {

    public static void main(String[] args) throws InvalideCustomerIDException, SQLException {
        SpringApplication.run(SpringBootBookingManagementProjectApplication.class, args);
    }
}
