package com.example.notification_service.kafka.order;

public record PurchaseResponse(
        Long productId,
        String name,
        String description,
        Double price,
        Double quantity
) {
}
