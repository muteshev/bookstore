package dev.zenfluxpro.bookstore.notifications.events;

import dev.zenfluxpro.bookstore.notifications.domain.NotificationService;
import dev.zenfluxpro.bookstore.notifications.domain.OrderEventEntity;
import dev.zenfluxpro.bookstore.notifications.domain.OrderEventRepository;
import dev.zenfluxpro.bookstore.notifications.domain.models.OrderCancelledEvent;
import dev.zenfluxpro.bookstore.notifications.domain.models.OrderCreatedEvent;
import dev.zenfluxpro.bookstore.notifications.domain.models.OrderDeliveredEvent;
import dev.zenfluxpro.bookstore.notifications.domain.models.OrderErrorEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderEventHandler {
    private final NotificationService notificationService;
    private final OrderEventRepository orderEventRepository;

    @RabbitListener(queues = "${notification.new-orders-queue}")
    public void handle(OrderCreatedEvent event) {
        if (orderEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate OrderCreatedEvent with eventId: {}", event.eventId());
            return;
        }
        log.info("Received a OrderCreatedEvent with orderNumber:{}: ", event.orderNumber());
        notificationService.sendOrderCreatedNotification(event);
        var orderEvent = new OrderEventEntity(event.eventId());
        orderEventRepository.save(orderEvent);
    }

    @RabbitListener(queues = "${notification.delivered-orders-queue}")
    public void handle(OrderDeliveredEvent event) {
        if (orderEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate OrderDeliveredEvent with eventId: {}", event.eventId());
            return;
        }
        log.info("Received a OrderDeliveredEvent with orderNumber:{}: ", event.orderNumber());
        notificationService.sendOrderDeliveredNotification(event);
        var orderEvent = new OrderEventEntity(event.eventId());
        orderEventRepository.save(orderEvent);
    }

    @RabbitListener(queues = "${notification.cancelled-orders-queue}")
    public void handle(OrderCancelledEvent event) {
        if (orderEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate OrderCancelledEvent with eventId: {}", event.eventId());
            return;
        }
        notificationService.sendOrderCancelledNotification(event);
        log.info("Received a OrderCancelledEvent with orderNumber:{}: ", event.orderNumber());
        var orderEvent = new OrderEventEntity(event.eventId());
        orderEventRepository.save(orderEvent);
    }

    @RabbitListener(queues = "${notification.error-orders-queue}")
    public void handle(OrderErrorEvent event) {
        if (orderEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate OrderErrorEvent with eventId: {}", event.eventId());
            return;
        }
        log.info("Received a OrderErrorEvent with orderNumber:{}: ", event.orderNumber());
        notificationService.sendOrderErrorEventNotification(event);
        OrderEventEntity orderEvent = new OrderEventEntity(event.eventId());
        orderEventRepository.save(orderEvent);
    }
}
