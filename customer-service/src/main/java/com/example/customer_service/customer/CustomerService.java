package com.example.customer_service.customer;

import com.example.customer_service.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    public Customer getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) throw new CustomerNotFoundException("Il cliente con id " + id + " non è presente!");
        return optionalCustomer.get();
    }

    public List<CustomerResponse> getAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }

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

    public GenericResponse deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
        return GenericResponse
                .builder()
                .status(HttpStatus.OK.value())
                .message("Cliente con id " + id + " cancellato correttamente")
                .build();
    }
}
