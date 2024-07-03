package dev.zenfluxpro.bookstore.orders.domain.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public record CreateOrderRequest(
        @Valid @NotEmpty(message = "Items cannot be empty")
                Set<dev.zenfluxpro.bookstore.orders.domain.models.OrderItem> items,
        @Valid dev.zenfluxpro.bookstore.orders.domain.models.Customer customer,
        @Valid Address deliveryAddress) {}
