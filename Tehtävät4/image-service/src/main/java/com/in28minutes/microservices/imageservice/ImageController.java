package com.in28minutes.microservices.imageservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    @GetMapping("/image-service/{currency}")
    public String getImage(@PathVariable String currency) {
        return "Image for currency: " + currency;
    }
}