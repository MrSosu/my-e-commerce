package com.example.notification_service.email;

import com.example.notification_service.kafka.order.PurchaseResponse;
import com.example.notification_service.kafka.payment.PaymentMethod;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String firstname,
            String lastname,
            Double amount,
            String reference,
            List<PurchaseResponse> products
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, String.valueOf(StandardCharsets.UTF_8));
        messageHelper.setFrom("adriani.marco.93@gmail.com");
        final String template = EmailTemplate.ORDER_CONFIRMATION.getTemplate();
        Map<String, Object> variables = new HashMap<>();
        variables.put("firstName", firstname);
        variables.put("lastName", lastname);
        variables.put("amount", amount);
        variables.put("reference", reference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getMessage());

        String htmlTemplate = templateEngine.process(template, context);
        messageHelper.setText(htmlTemplate);
        messageHelper.setTo(destinationEmail);
        mailSender.send(message);
    }

    public void sendPaymentConfirmationEmail(
            String orderReference,
            Double amount,
            PaymentMethod paymentMethod,
            String firstname,
            String lastname,
            String destinationEmail
    )
        throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, String.valueOf(StandardCharsets.UTF_8));
        messageHelper.setFrom("adriani.marco.93@gmail.com");
        final String template = EmailTemplate.PAYMENT_CONFIRMATION.getTemplate();
        Map<String, Object> variables = new HashMap<>();
        variables.put("firstName", firstname);
        variables.put("lastName", lastname);
        variables.put("amount", amount);
        variables.put("reference", orderReference);
        variables.put("paymentMethod", paymentMethod);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getMessage());

        String htmlTemplate = templateEngine.process(template, context);
        messageHelper.setText(htmlTemplate);
        messageHelper.setTo(destinationEmail);
        mailSender.send(message);
    }


}
