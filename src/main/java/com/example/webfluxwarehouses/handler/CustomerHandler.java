package com.example.webfluxwarehouses.handler;

import com.example.webfluxwarehouses.entity.Customer;
import com.example.webfluxwarehouses.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller class for Customer requests
 */
@Configuration
@RequiredArgsConstructor
public class CustomerHandler {
    private final CustomerService customerService;

    /**
     * A simple method to retrieve customer by id
     * which is passed as a path variable
     * @param request
     * @return requested customer
     */
    public Mono<ServerResponse> retrieveCustomerById(ServerRequest request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        Mono<Customer> searchedUser = customerService.findById(id);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(searchedUser, Customer.class);
    }

    /**
     * A simple method create customer, customer data is passed as a request body
     * @param request
     * @return created customer
     */
    public Mono<ServerResponse> createCustomer(ServerRequest request) {
        Mono<Customer> userToCreate = request.bodyToMono(Customer.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        userToCreate.flatMap(customerService::createCustomer),
                        Customer.class
                );
    }

    /**
     * A simple method to retrieve all customers
     * @param request
     * @return all customers
     */
    public Mono<ServerResponse> retrieveAllCustomers(ServerRequest request) {
        Flux<Customer> users = customerService.findAll();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(users, Customer.class);
    }
}
