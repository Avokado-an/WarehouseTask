package com.example.webfluxwarehouses.repository;

import com.example.webfluxwarehouses.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for customer
 */
@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
}
