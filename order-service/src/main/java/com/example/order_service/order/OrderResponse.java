package com.example.order_service.order;

import com.example.order_service.payment.PaymentMethod;
import lombok.Builder;

@Builder
public record OrderResponse(
        String reference,
        PaymentMethod paymentMethod,
        Double amount,
        Long customerId
) {
}
