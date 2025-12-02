package main.com.orkhangasanov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "main.com.orkhangasanov.client")
public class BookingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookingServiceApplication.class, args);
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