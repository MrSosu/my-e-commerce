package com.example.products.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Long productId,
        @Positive(message = "Quantità deve essere positiva!")
        double quantity
) {
}
