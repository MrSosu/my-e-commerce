package com.example.customer_service.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String exception,
        String message
) {
}
