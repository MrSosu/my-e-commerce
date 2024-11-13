package com.example.notification_service.kafka;

import com.example.notification_service.email.EmailService;
import com.example.notification_service.kafka.order.OrderConfirmation;
import com.example.notification_service.kafka.payment.PaymentConfirmation;
import com.example.notification_service.notification.Notification;
import com.example.notification_service.notification.NotificationRepository;
import com.example.notification_service.notification.NotificationType;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationConsumer {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        notificationRepository.save(Notification
                .builder()
                .type(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build());
        /*emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                orderConfirmation.customer().firstName(),
                orderConfirmation.customer().lastName(),
                orderConfirmation.totalAmount(),
                orderConfirmation.reference(),
                orderConfirmation.products()
        ); */
    }

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentConfirmationNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        notificationRepository.save(Notification
                .builder()
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build());
        /*emailService.sendPaymentConfirmationEmail(
                paymentConfirmation.orderReference(),
                paymentConfirmation.amount(),
                paymentConfirmation.paymentMethod(),
                paymentConfirmation.firstname(),
                paymentConfirmation.lastname(),
                paymentConfirmation.email()
        ); */
    }

}
