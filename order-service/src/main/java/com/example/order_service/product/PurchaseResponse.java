package com.example.order_service.product;

public record PurchaseResponse(
        Long productId,
        String name,
        String description,
        Double price,
        Double quantity
) {
}
