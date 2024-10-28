package com.example.customer_service.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest request) {
        return Customer
                .builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse toResponse(Customer customer) {
        return CustomerResponse
                .builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .build();
    }

    public Customer toEntityFromUpdate(CustomerUpdateRequest request) {
        return Customer
                .builder()
                .id(request.id())
                .email(request.email())
                .address(request.address())
                .build();
    }

}
