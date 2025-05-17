package com.example.spring.mappings;

import com.example.spring.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<Customer> execute(Integer input){
        return ResponseEntity.ok(customerRepository.findById(input).get());
    }
}
