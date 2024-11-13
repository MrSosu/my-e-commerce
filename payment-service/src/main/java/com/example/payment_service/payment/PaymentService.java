package com.example.payment_service.payment;

import com.example.payment_service.kafka.PaymentConfirmation;
import com.example.payment_service.kafka.PaymentNotificationProducer;
import com.example.payment_service.kafka.PaymentConfirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private PaymentNotificationProducer producer;

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {

        var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        // invio della notifica tramite Kafka
        producer.sendNotification(PaymentConfirmation
                .builder()
                        .orderReference(paymentRequest.orderReference())
                        .paymentMethod(paymentRequest.paymentMethod())
                        .amount(paymentRequest.amount())
                        .firstname(paymentRequest.customer().firstName())
                        .lastname(paymentRequest.customer().lastName())
                        .email(paymentRequest.customer().email())
                .build());
        return paymentMapper.toPaymentResponse(payment, paymentRequest);
    }
}
