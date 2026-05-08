package com.in28minutes.microservices.forexservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ForexServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForexServiceApplication.class, args);
    }
}