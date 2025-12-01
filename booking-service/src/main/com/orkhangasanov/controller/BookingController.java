package main.com.orkhangasanov.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final RestTemplate restTemplate;
    private static final String HOTEL_SERVICE_CB = "hotelServiceCircuitBreaker";

    @Autowired
    public BookingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/status")
    public String getBookingStatus() {
        return "Booking service is up and running!";
    }

    @GetMapping("/hotels")
    @CircuitBreaker(name = HOTEL_SERVICE_CB, fallbackMethod = "getAvailableHotelsFallback")
    public String getAvailableHotels() {
        String response = restTemplate.getForObject("http://hotel-service/hotels", String.class);
        return "Booking Service called Hotel Service --> " + response;
    }

    public String getAvailableHotelsFallback(Throwable throwable) {
        return "Booking Service: Hotel Service is unavailable";
    }
}

/*
 * Resilience4j — a lightweight fault tolerance library for Java 8+,
 * inspired by Netflix Hystrix. It is designed for functional programming
 * and helps build resilient distributed systems.
 *
 * Core Features:
 *
 * 1. Circuit Breaker:
 *    - Prevents cascading failures by temporarily blocking calls to failing services.
 *
 * 2. Retry:
 *    - Automatically retries failed operations with configurable backoff strategies.
 *
 * 3. Rate Limiter:
 *    - Controls the number of
 * calls allowed within a specified time window.
 *
 * 4. Bulkhead:
 *    - Limits concurrent executions to prevent resource exhaustion (similar to thread pools).
 *
 * 5. Time Limiter:
 *    - Defines timeouts for asynchronous operations to avoid indefinite waits.

 */