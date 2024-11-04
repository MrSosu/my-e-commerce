package com.example.order_service.customer;

public record CustomerResponse (
        Long id,
        String firstName,
        String lastName,
        String email
){
}
