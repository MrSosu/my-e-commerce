package com.example.payment_service.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {


    public Payment toPayment(PaymentRequest request) {

        return Payment.builder()
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();

    }

    public PaymentResponse toPaymentResponse(Payment payment, PaymentRequest request) {

        return PaymentResponse
                .builder()
                .amount(payment.getAmount())
                .orderReference(request.orderReference())
                .build();
    }
}
