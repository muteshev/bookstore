package dev.zenfluxpro.bookstore.orders.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQDemoListener {
    @RabbitListener(queues = "${orders.new-orders-queue}")
    public void handleNewOrder(MyDemoPayload payload) {
        log.info("***** handleNewOrder *****");
        log.info("New order: {}", payload.content());
    }

    @RabbitListener(queues = "${orders.delivered-orders-queue}")
    public void handleDeliveredOrder(MyDemoPayload payload) {
        log.info("***** handleDeliveredOrder *****");
        log.info("Delivered order: {}", payload.content());
    }
}
