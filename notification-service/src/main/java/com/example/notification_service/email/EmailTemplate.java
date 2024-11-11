package com.example.notification_service.email;

import lombok.Getter;

@Getter
public enum EmailTemplate {

    PAYMENT_CONFIRMATION("payment-confirmation.html", "Pagamento avvenuto con successo"),
    ORDER_CONFIRMATION("order-confirmation.html", "Ordine effettuato con successo");

    private final String template;
    private final String message;

    EmailTemplate(String template, String message) {
        this.template = template;
        this.message = message;
    }

}
