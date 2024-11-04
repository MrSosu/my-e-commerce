package com.example.order_service.order;

import lombok.Builder;

@Builder
public record OrderResponse(
        String reference,
        PaymentMethod paymentMethod,
        Double amount,
        Long customerId
) {
}
