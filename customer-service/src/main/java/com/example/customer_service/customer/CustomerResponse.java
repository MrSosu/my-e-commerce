package com.example.customer_service.customer;

import lombok.Builder;

@Builder
public record CustomerResponse (
        Long id,
        String email
) {
}
