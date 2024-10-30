package com.example.products.product;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double availableQuantity,
        Double price,
        Long categoryId,
        String categoryName,
        String categoryDescription
) {
}
