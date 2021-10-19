package com.example.webfluxwarehouses.repository;

import com.example.webfluxwarehouses.entity.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Repository class for order
 */
@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
    /**
     * find all orders made by the customer
     * @param customerId id of the customer
     * @return orders made by the customer
     */
    Flux<Order> findAllByCustomerId(Long customerId);
}
