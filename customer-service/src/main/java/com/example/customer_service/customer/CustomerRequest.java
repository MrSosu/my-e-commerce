package com.example.customer_service.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest (
        @NotNull(message = "Il nome del customer non può essere null!")
        String firstName,
        @NotNull(message = "Il cognome del customer non può essere null!")
        String lastName,
        @NotNull(message = "L'email del customer è obbligatoria!")
        @Email(message = "Email inserita non valida")
        String email,
        String address
){
}
