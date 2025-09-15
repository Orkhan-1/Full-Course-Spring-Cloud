package com.orkhangasanov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }
}

/*
  ================================
          Microservices Setup
  ================================

  10 Microservices

  Service A -> Service B (ip:port)
  Service B -> Service D (ip:port)
  Service C -> Service D (ip:port)












  ================================
         Discovery Server
         (Eureka Registry)
  ================================

  Service A -> registers in Discovery Server (A-service)
  Service B -> registers in Discovery Server (B-service)
  Service C -> registers in Discovery Server (C-service)
  Service D -> registers in Discovery Server (D-service)


  ================================
        Service Communication
  ================================

  Service A -> Service B (B-service)
  Service B -> Service D (D-service)


  ================================
       Heartbeat Mechanism
  ================================

  Discovery Server (Eureka)
  (Service sends a heartbeat every 30 seconds)

  Service A -> Discovery Server (heartbeat -> "I'm alive")
  Service B -> Discovery Server (heartbeat -> "I'm alive")
  Service C -> Discovery Server (heartbeat -> "I'm alive")
  Service D -> Discovery Server (heartbeat -> "I'm alive")
*/