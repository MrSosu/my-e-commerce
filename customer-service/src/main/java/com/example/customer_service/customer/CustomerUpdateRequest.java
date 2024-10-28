package com.example.customer_service.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CustomerUpdateRequest(
        @NotNull(message = "Se l'id è null come posso aggiornare l'utente!")
        Long id,
        @Email
        String email,
        String address
) {
}
