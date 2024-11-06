package com.example.notification_service.kafka.order;

public record CustomerResponse (
        Long id,
        String firstName,
        String lastName,
        String email
){
}