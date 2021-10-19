package com.example.webfluxwarehouses.service;

import com.example.webfluxwarehouses.entity.Customer;
import com.example.webfluxwarehouses.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service class for customers
 */
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * Simple method to retrieve all customers
     * @return all customers
     */
    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }


    /**
     * Method to retrieve customer by id
     * @param id id of the customer
     * @return requested customer
     */
    public Mono<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    /**
     * Method to create a new customer
     * @param customer the customer to create
     * @return created customer
     */
    public Mono<Customer> createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
