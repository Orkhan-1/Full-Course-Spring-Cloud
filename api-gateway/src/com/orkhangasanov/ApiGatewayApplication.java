package com.orkhangasanov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}


/*
  ╔══════════════════════════════════════════════════════════╗
  ║                 API GATEWAY INTRODUCTION                 ║
  ╚══════════════════════════════════════════════════════════╝

    HOTEL BOOKING SYSTEM (Microservices Setup)
  ─────────────────────────────────────────────
  We currently have multiple microservices:

      [ Booking Service ]    -->  [ Payment Service ]
             │
             ├──> [ Hotel Service ]
             │
             ├──> [ Notification Service ]
             │
             └──> [ User Service ]

 Each service communicates directly with others:
      /book/hotels
      /payments
      /notifications
      /users
      ...

  PROBLEM:
  ───────────
  - Frontend or mobile clients must know every microservice’s URL (ip:port).
  - Hard to maintain and scale.
  - Security risk — every service is exposed directly.

  SOLUTION: INTRODUCE AN API GATEWAY
  ─────────────────────────────────────
  One single entry point for **all** client requests.

      ┌───────────────────────────────┐
      │          CLIENT APP           │
      └──────────────┬────────────────┘
                     │
                     ▼
           ┌──────────────────────┐
           │     API GATEWAY      │
           │ (Single Entry Point) │
           └───────┬──────┬───────┘
                   │      │
          ┌────────┘      └────────┐
          ▼                         ▼
   [ Booking Service ]       [ Payment Service ]
   [ Hotel Service ]         [ Notification Service ]
   [ User Service ]

  RESPONSIBILITIES OF API GATEWAY
  ─────────────────────────────────
  -Request Routing — send each request to the correct service
  -Authentication & Authorization
  -Logging and Monitoring
  -Rate Limiting & Throttling
  -Response Aggregation (combine multiple service results)

  TECHNOLOGIES
  ─────────────────────────
  1) Netflix Zuul – part of the classic Netflix OSS stack.
  2) Spring Cloud Gateway – modern, reactive, high-performance alternative.
*/
