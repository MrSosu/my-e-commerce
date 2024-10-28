package com.example.customer_service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toEntity(request);
        customerRepository.save(customer);
        return customerMapper.toResponse(customer);
    }

    public CustomerResponse updateCustomer(CustomerUpdateRequest request) {
        Customer customer = customerMapper.toEntityFromUpdate(request);
        customerRepository.save(customer);
        return customerMapper.toResponse(customer);
    }
}
