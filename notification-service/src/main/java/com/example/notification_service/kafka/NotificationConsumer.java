package com.example.notification_service.kafka;

import com.example.notification_service.kafka.order.OrderConfirmation;
import com.example.notification_service.kafka.payment.PaymentConfirmation;
import com.example.notification_service.notification.Notification;
import com.example.notification_service.notification.NotificationRepository;
import com.example.notification_service.notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationConsumer {

    @Autowired
    private NotificationRepository notificationRepository;

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {
        notificationRepository.save(Notification
                .builder()
                .type(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build());

    }

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentConfirmationNotification(PaymentConfirmation paymentConfirmation) {
        notificationRepository.save(Notification
                .builder()
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build());
    }

}
