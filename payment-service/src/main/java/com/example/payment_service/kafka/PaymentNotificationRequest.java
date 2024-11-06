package com.example.payment_service.kafka;

import com.example.payment_service.payment.PaymentMethod;
import lombok.Builder;

@Builder
public record PaymentNotificationRequest(
        String orderReference,
        Double amount,
        PaymentMethod paymentMethod,
        String firstname,
        String lastname,
        String email
) {
}
