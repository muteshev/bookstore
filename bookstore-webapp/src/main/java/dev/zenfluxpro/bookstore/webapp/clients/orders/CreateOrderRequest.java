package dev.zenfluxpro.bookstore.webapp.clients.orders;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

public record CreateOrderRequest(
        @NotEmpty(message = "Items cannot be empty.")
                Set<dev.zenfluxpro.bookstore.webapp.clients.orders.OrderItem> items,
        @Valid dev.zenfluxpro.bookstore.webapp.clients.orders.Customer customer,
        @Valid Address deliveryAddress)
        implements Serializable {}
