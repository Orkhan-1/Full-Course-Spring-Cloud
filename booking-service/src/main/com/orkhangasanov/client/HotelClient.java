package main.com.orkhangasanov.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "hotel-service") // Name registered in Eureka
public interface HotelClient {

    @GetMapping("/hotels")
    String getHotels();
}
