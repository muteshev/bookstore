package dev.zenfluxpro.bookstore.orders.web.controllers;

import dev.zenfluxpro.bookstore.orders.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
class RabbitMQDemoController {
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;

    @PostMapping("/send")
    public void sendMessage(@RequestBody MyDemoMessage message) {
        log.info("***** sendMessage *****");
        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), message.routingKey(), message.payload());
    }
}

record MyDemoMessage(String routingKey, MyDemoPayload payload) {}

record MyDemoPayload(String content) {}
