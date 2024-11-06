package com.example.payment_service.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public record PaymentResponse(
        Long id,
        Double amount,
        String orderReference
) {
}
