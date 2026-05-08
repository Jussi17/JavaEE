package com.in28minutes.microservices.forexservice;

import com.in28minutes.microservices.forexservice.bean.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private ExchangeValueRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        repository.save(new ExchangeValue(10001L, "USD", "INR", 65.0));
        repository.save(new ExchangeValue(10002L, "EUR", "INR", 75.0));
        repository.save(new ExchangeValue(10003L, "AUD", "INR", 25.0));
    }
}