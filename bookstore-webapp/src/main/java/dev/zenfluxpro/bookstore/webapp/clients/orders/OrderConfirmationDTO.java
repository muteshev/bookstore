package dev.zenfluxpro.bookstore.webapp.clients.orders;

public record OrderConfirmationDTO(
        String orderNumber, dev.zenfluxpro.bookstore.webapp.clients.orders.OrderStatus status) {}
