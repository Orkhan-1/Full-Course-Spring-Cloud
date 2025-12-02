package main.com.orkhangasanov.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import main.com.orkhangasanov.client.HotelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private HotelClient hotelClient;
    private static final String HOTEL_SERVICE_CB = "hotelServiceCircuitBreaker";

    @Value("${message}")
    private String message;

    @Autowired
    public BookingController() {

    }

    @GetMapping("/status")
    public String getBookingStatus() {
        return "Booking service is up and running!";
    }

    @GetMapping("/hotels")
    @CircuitBreaker(name = HOTEL_SERVICE_CB, fallbackMethod = "getAvailableHotelsFallback")
    public String getAvailableHotels() {
        // String response = restTemplate.getForObject("http://hotel-service/hotels", String.class);
        // return "Booking Service called Hotel Service --> " + response;
        String response = hotelClient.getHotels();
        return "Booking Service: " + message;
    }

    public String getAvailableHotelsFallback(Throwable throwable) {
        return "Booking Service: Hotel Service is unavailable";
    }
}

/*
  ╔══════════════════════════════════════════════════════════╗
  ║              SPRING CLOUD OPENFEIGN                      ║
  ╚══════════════════════════════════════════════════════════╝

    MICROSERVICE COMMUNICATION PATTERNS
  ─────────────────────────────────────────
  Traditional HTTP client approach:

      [Service A] ──[RestTemplate/HttpClient]──> [Service B]
          │                   │
          │                   │
          ▼                   ▼
     Verbose code       Manual serialization
     Error handling     Connection management
     URL construction   Retry logic

  PROBLEM:
  ───────────
  - Repetitive boilerplate code in every service
  - Error-prone string manipulation for URLs/parameters
  - Difficult to maintain and test
  - No type safety when calling other services
  - Service discovery integration requires manual coding

  SOLUTION: OPENFEIGN - DECLARATIVE HTTP CLIENT
  ───────────────────────────────────────────────
  Write interfaces, not implementation code

      [Service A] ──[Feign Interface]──> [Service B]
          │                   │
          │                   │
          ▼                   ▼
     Clean interface    Auto-generated HTTP client
     Type-safe calls    Built-in serialization
     Annotations        Service discovery ready

  HOW IT WORKS
  ───────────────
  1) Feign talks to **Eureka** to discover the service location.
  2) Uses **LoadBalancer** for client-side load balancing.
  3) Sends HTTP requests behind the scenes using WebClient or RestTemplate.

  ADVANTAGES
  ─────────────
  - No manual RestTemplate boilerplate
  - Easy interface-based service-to-service calls
  - Built-in integration with Eureka, LoadBalancer, and Spring Cloud Config
  - Supports interceptors, retries, and fault tolerance (with Resilience4j)

*/
