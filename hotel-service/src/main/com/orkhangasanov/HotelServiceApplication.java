package main.com.orkhangasanov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelServiceApplication.class, args);
    }
}

/*


  ╔══════════════════════════════════════════════════════════╗
  ║               MONOLITH → MICROSERVICES                   ║
  ╚══════════════════════════════════════════════════════════╝

  1) MONOLITHIC ARCHITECTURE - HOTEL SYSTEM
  -------------------------------
        +------------------------------------------------------+
        |                Hotel Management App                  |
        |------------------------------------------------------|
        | Modules:                                             |
        |  • Booking                                           |
        |  • Hotel                                             |
        |  • Payment                                           |
        | Technology Stack:                                    |
        |  • Spring Boot                                       |
        |  • Single Database (MySQL)                           |
        |  • Single deployable jar                             |
        +------------------------------------------------------+
                  | REST / JDBC / Internal calls
                  ▼
               [Client Requests]

  ⚠️ ISSUES WITH MONOLITHIC HOTEL SYSTEM:
  - Booking changes require redeploying entire app
  - Payment load spikes affect Hotel and Booking modules
  - Hard to scale individual modules
  - Tech stack locked: can't use different DB for each module
  - Harder to maintain and test

  ------------------------------------------------------------

  2) MICROSERVICES ARCHITECTURE - HOTEL SYSTEM
  -------------------------------
        +-----------------+       +-----------------+       +-----------------+
        |  Booking Service|       |  Hotel Service  |       | Payment Service |
        | • Java (Spring) |       | • Python        |       | • GO            |
        | • MySQL         |       | • MongoDB       |       | • PostgreSQL    |
        +-----------------+       +-----------------+       +-----------------+
                |                         |                         |
                | REST / HTTP / gRPC / Messaging / Kafka            |
                ▼                         ▼                         ▼
        +-----------------------------------------------------------------+
        |                 MICROSERVICES ECOSYSTEM                        |
        |  • Independent Deployment & Scaling                            |
        |  • Loosely Coupled via APIs                                    |
        |  • Database per Service Pattern                                |
        |  • Domain-Driven Design Boundaries                             |
        +-----------------------------------------------------------------+

  ✅ BENEFITS OF SPLITTING HOTEL SYSTEM:
  - Booking, Hotel, Payment can scale independently
  - Each service can use its optimal database
  - Faster development & deployment per module
  - Clear separation of responsibilities (DDD)
  - Fault isolation: Payment failure won't crash Hotel service

  ------------------------------------------------------------

️  3) Spring Cloud is a set of tools and frameworks for building distributed systems
     and microservices architectures on top of the Spring Boot ecosystem

     Netflix Open Source Software (OSS)

        +-----------------+    +--------------------+
        |  Microservices  |    |   Netflix OSS      |
        +-----------------+    +--------------------+
        | Service Discovery|<->| Eureka             |
        | Load Balancing   |<->| Ribbon → replaced  |
        | Config Server    |<->| Archaius → replaced|
        | Circuit Breaker  |<->| Hystrix → replaced |
        | API Gateway      |<->| Zuul → replaced    |
        +------------------+   +--------------------+

  - Spring Cloud simplifies microservices operations:
      • Type-safe clients (Feign)
      • Load balancing (Spring Cloud LoadBalancer)
      • Fault tolerance (Resilience4j)
      • Config management (Spring Cloud Config)
      • Tracing (Spring Cloud Sleuth + Micrometer)

  4) Spring Cloud Replacement of Netflix OSS

  -------------------------------
  +----------------+         +--------------------------+
  | Netflix OSS    |         | Spring Cloud Replacement |
  +----------------+         +--------------------------+
  | Ribbon         | ------> | Spring Cloud LoadBalancer |
  | Hystrix        | ------> | Resilience4j             |
  | Zuul           | ------> | Spring Cloud Gateway     |
  | Archaius       | ------> | Spring Cloud Config      |
  | Eureka         | ------> | Eureka (still used)      |
  | Feign          | ------> | OpenFeign                |
  +----------------+         +--------------------------+

*/


