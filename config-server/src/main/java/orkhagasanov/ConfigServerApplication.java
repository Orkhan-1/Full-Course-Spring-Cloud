package orkhagasanov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}

/*
  ╔══════════════════════════════════════════════════════════╗
  ║       CENTRALIZED CONFIG WITH SPRING CLOUD CONFIG        ║
  ╚══════════════════════════════════════════════════════════╝

    CENTRALIZED CONFIGURATION MANAGEMENT
  ─────────────────────────────────────────
  Traditional approach:

      [Service A] → application.yml
      [Service B] → application.yml
      [Service C] → application.yml

  PROBLEM:
  ───────────
  - Each service stores its own configuration.
  - Updating a property (e.g., DB URL, message, feature flag)
    requires redeploying **every** service.
  - No centralized visibility or version control.
  - Hard to roll back or audit config changes.

  SOLUTION: SPRING CLOUD CONFIG SERVER
  ───────────────────────────────────────────────
  Store all configurations in a **single Git repository**.
  Each microservice pulls its config remotely at startup.

          ┌────────────────────────────────────┐
          │   spring-config-repo (Git)         │
          │   ├── application.yml              │
          │   ├── booking-service.yml          │
          │   └── hotel-service.yml            │
          └────────────────────────────────────┘
                         ▲
                         │ HTTP (port 8888)
                         ▼
          ┌────────────────────────────────────┐
          │  Config Server                     │
          │  spring.cloud.config.server.git.uri│
          └────────────────────────────────────┘
                         ▲
                         │ spring.config.import
                         ▼
          ┌────────────────────────────────────┐
          │  Booking Service / Hotel Service   │
          │  (clients)                         │
          └────────────────────────────────────┘

  HOW IT WORKS
  ───────────────
  1) **Config Server** starts and reads YAML files from the Git repo.
  2) **Client Services** connect to it at startup.
  3) Properties are fetched and merged into the Environment before context loads.
  4) Configuration changes can be versioned and refreshed dynamically.

  ADVANTAGES
  ─────────────
  - Centralized config management across all microservices.
  - Version-controlled via Git (rollback anytime).
  - Environment-specific configs supported (dev, prod, etc.).
  - Works seamlessly with Eureka, Gateway, and OpenFeign.
  - Supports dynamic refresh via /actuator/refresh or Spring Cloud Bus.

*/

