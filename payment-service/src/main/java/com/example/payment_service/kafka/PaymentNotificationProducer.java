package com.example.payment_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;


@Service
public class PaymentNotificationProducer {

    @Autowired
    private KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;

    public void sendNotification(PaymentConfirmation request) {
        Message<PaymentConfirmation> message = MessageBuilder
                .withPayload(request)
                .setHeader(TOPIC, "payment-topic")
                .build();
        kafkaTemplate.send(message);
    }

}
