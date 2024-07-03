package dev.zenfluxpro.bookstore.notifications.domain.models;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderErrorEvent(
        String eventId,
        String orderNumber,
        Set<dev.zenfluxpro.bookstore.notifications.domain.models.OrderItem> items,
        Customer customer,
        Address deliveryAddress,
        String reason,
        LocalDateTime createdAt) {}
