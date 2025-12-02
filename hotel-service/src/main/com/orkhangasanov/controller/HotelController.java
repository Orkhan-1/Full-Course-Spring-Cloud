package main.com.orkhangasanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hotels")
class HotelController {

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${message}")
    private String message;

    @GetMapping
    public String getHotels() {
       return "Hotels service: " + message;
    }
}