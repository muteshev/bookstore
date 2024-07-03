package dev.zenfluxpro.bookstore.notifications.domain.models;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderDeliveredEvent(
        String eventId,
        String orderNumber,
        Set<dev.zenfluxpro.bookstore.notifications.domain.models.OrderItem> items,
        Customer customer,
        Address deliveryAddress,
        LocalDateTime createdAt) {}
