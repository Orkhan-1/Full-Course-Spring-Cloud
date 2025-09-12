package main.com.orkhangasanov.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
class HotelController {
    @GetMapping
    public List<String> getHotels() {
        return List.of("Hotel One", "Hotel Two", "Hotel Tree");
    }
}