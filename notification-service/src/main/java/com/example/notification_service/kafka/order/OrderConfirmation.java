package com.example.notification_service.kafka.order;

import com.example.notification_service.kafka.payment.PaymentMethod;

import java.util.List;

public record OrderConfirmation(
        String reference,
        Double totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
