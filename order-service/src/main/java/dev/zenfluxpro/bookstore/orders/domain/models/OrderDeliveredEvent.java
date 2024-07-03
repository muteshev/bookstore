package dev.zenfluxpro.bookstore.orders.domain.models;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderDeliveredEvent(
        String eventId,
        String orderNumber,
        Set<dev.zenfluxpro.bookstore.orders.domain.models.OrderItem> items,
        Customer customer,
        Address deliveryAddress,
        LocalDateTime createdAt) {}
