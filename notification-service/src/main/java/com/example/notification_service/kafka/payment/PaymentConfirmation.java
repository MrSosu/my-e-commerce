package com.example.notification_service.kafka.payment;

public record PaymentConfirmation(
        String orderReference,
        Double amount,
        PaymentMethod paymentMethod,
        String firstname,
        String lastname,
        String email
) {
}
