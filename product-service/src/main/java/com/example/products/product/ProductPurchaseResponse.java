package com.example.products.product;

public record ProductPurchaseResponse(
        Long productId,
        String name,
        String description,
        Double price,
        double quantity
) {
}