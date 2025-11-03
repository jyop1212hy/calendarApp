package com.calendarapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CalendarAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalendarAppApplication.class, args);
    }
}
