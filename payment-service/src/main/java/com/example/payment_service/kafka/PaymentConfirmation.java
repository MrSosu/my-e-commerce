package com.example.payment_service.kafka;

import com.example.payment_service.payment.PaymentMethod;
import lombok.Builder;

@Builder
public record PaymentConfirmation(
        String orderReference,
        Double amount,
        PaymentMethod paymentMethod,
        String firstname,
        String lastname,
        String email
) {
}
