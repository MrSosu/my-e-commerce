package com.example.order_service.payment;

import com.example.order_service.customer.CustomerResponse;
import lombok.Builder;

@Builder
public record PaymentRequest(
        Double amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,  // Rinomina da "reference" a "orderReference"
        CustomerResponse customer    // Assicurati che il tipo sia coerente con quello usato nel servizio `payment
) {
}

