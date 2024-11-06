package com.example.payment_service.payment;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentRequest(
    @NotNull(message = "l'ammontare del pagamento è obbligatorio")
    @Positive(message = "l'ammontare del pagamento non può essere negativo")
    Double amount,
    @NotNull(message = "il metodo di pagamento è obbligatorio")
    PaymentMethod paymentMethod,
    @NotNull(message = "l'id dell'ordine è obbligatorio")
    Long orderId,
    @NotNull(message = "il riferimento all'ordine è obbligatorio")
    String orderReference,
    Customer customer
) {
}
