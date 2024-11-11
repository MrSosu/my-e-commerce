package com.example.payment_service.payment;

import lombok.Builder;

@Builder
public record PaymentResponse(
        Long id,
        Double amount,
        String orderReference
) {
}
