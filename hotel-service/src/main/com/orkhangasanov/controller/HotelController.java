package main.com.orkhangasanov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotels")
class HotelController {

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${message}")
    private String message;

    private static final Logger log = LoggerFactory.getLogger(HotelController.class);

    @GetMapping
    public String getHotels() {
       log.info("HotelService responding from port {}", serverPort);
       return "Hotels service: " + message;
    }
}

/*
  ╔══════════════════════════════════════════════════════════════════════════╗
  ║             DISTRIBUTED TRACING WITH MICROMETER + ZIPKIN                 ║
  ╚══════════════════════════════════════════════════════════════════════════╝

    OBSERVABILITY IN MICROSERVICES
  ─────────────────────────────────────────
  Once we have multiple microservices (Booking, Hotel, Payment, etc.),
  a single user request can travel across **many** services.

      CLIENT → API Gateway → Booking Service → Hotel Service → Payment Service

  PROBLEM:
  ───────────
  - When something is slow or failing, it’s hard to know *where* it happened.
  - Logs are scattered across services.
  - No unified trace of a single request.
  - Hard to correlate logs for the same transaction.

  OLD SOLUTION (Spring Boot 2.x Era):
  ────────────────────────────────────
  → `Spring Cloud Sleuth` + `Zipkin`
  Sleuth automatically tagged each request with traceId/spanId,
  and reported data to Zipkin.

 -But starting with **Spring Boot 3.x / Spring Cloud 2023.x**,
 Sleuth has been **removed** and replaced with:

         **Micrometer Tracing**

  ─────────────────────────────────────────
  MICROMETER TRACING STACK (MODERN APPROACH)
  ─────────────────────────────────────────
  Micrometer Tracing is now the official solution for distributed tracing.

          ┌────────────────────────────────────────┐
          │             MICROMETER TRACING         │
          └────────────────────────────────────────┘
                ▲                        ▲
                │                        │
       ┌───────────────┐       ┌────────────────────┐
       │ Brave Bridge  │       │ OpenTelemetry Bridge│
       │ (Zipkin)      │       │ (Jaeger/OTEL)      │
       └───────────────┘       └────────────────────┘
                ▲                        ▲
                │                        │
       ┌────────────┐           ┌──────────────┐
       │  Zipkin UI │           │  Jaeger UI   │
       └────────────┘           └──────────────┘

  ─────────────────────────────────────────
  HOW IT WORKS (End-to-End Flow)
  ─────────────────────────────────────────
  1) Each request gets a **traceId** and **spanId**.
  2) Those IDs are automatically propagated between microservices.
  3) The Micrometer Tracing system records timings, errors, metadata.
  4) Data is sent to **Zipkin** via HTTP (port 9411).
  5) Zipkin visualizes the call chain across all services.

  Example flow:
  ───────────────
      [Client]
         │
         ▼
   ┌───────────────┐
   │ API Gateway   │──traceId=abc123
   └──────┬────────┘
          │
          ▼
   ┌───────────────┐
   │ Booking Svc   │──spanId=001
   └──────┬────────┘
          │
          ▼
   ┌───────────────┐
   │ Hotel Svc     │──spanId=002
   └───────────────┘
  ─────────────────────────────────────────
  ADVANTAGES
  ─────────────────────────────────────────
 - Distributed tracing out-of-the-box
 - Integrates with Feign, RestTemplate, WebClient automatically
 - Zero manual trace propagation
 - View latency and bottlenecks visually
 - Replaceable backend (Zipkin, Jaeger, OTEL)

*/
