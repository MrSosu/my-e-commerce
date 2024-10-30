package com.example.customer_service.customer;

import lombok.Builder;

@Builder
public record GenericResponse(
        Integer status,
        String message
) {
}
