package com.example.payment_service.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        @NotNull(message = "l'id è richiesto")
        Long id,
        @NotNull(message = "il nome è richiesto")
        String firstName,
        @NotNull(message = "il cognome è richiesto")
        String lastName,
        @NotNull(message = "l'email è richiesta")
        @Email(message = "l'email inserita non è corretta")
        String email
) {
}
