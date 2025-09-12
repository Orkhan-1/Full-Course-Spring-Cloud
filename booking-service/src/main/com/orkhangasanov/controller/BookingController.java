package main.com.orkhangasanov.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BookingController {

    @Value("${spring.application.name}")
    private String serviceName;

    @GetMapping("/booking/status")
    public Map<String, String> getBookingStatus() {
        return Map.of("service", serviceName, "status", "Booking service is up and running!");
    }
}