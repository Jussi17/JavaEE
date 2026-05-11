package com.codenotfound.jms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringJmsApplicationTest {

    @Autowired
    private Sender sender;

    @Value("${queue.name}")
    private String queueName;

    @Test
    public void testSend() throws InterruptedException {
        sender.send(queueName, "Hello World!");
        Thread.sleep(1000);
    }
}