package com.codenotfound.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "${queue.name}")
    public void receive(String message) {
        System.out.println("Received: " + message);
    }
}