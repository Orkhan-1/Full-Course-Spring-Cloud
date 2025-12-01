package main.com.orkhangasanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class BookingController {

    private RestTemplate restTemplate;

    @Autowired
    public BookingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/booking/status")
    public String getBookingStatus() {
        return "Booking service is up and running!";
    }

    @GetMapping("/book/hotels")
    public String getAvailableHotels() {
        String response = restTemplate.getForObject("http://hotel-service/hotels", String.class);
        return "Booking Service called Hotel Service --> " + response;
    }

}