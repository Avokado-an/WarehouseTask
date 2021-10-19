package com.example.webfluxwarehouses.handler;

import com.example.webfluxwarehouses.entity.Order;
import com.example.webfluxwarehouses.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Controller class for Order requests
 */
@Configuration
@RequiredArgsConstructor
public class OrderHandler {
    private final OrderService orderService;

    /**
     * Method for order creation
     * id of the order owner is passed as a path variable
     * order data is passed as a request body
     * @param request
     * @return created order
     */
    public Mono<ServerResponse> createOrder(ServerRequest request) {
        Long customerId = Long.parseLong(request.pathVariable("id"));
        Mono<Order> orderToCreate = request.bodyToMono(Order.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        orderToCreate.flatMap(order -> orderService.createOrder(customerId, order)),
                        Order.class
                );
    }

    /**
     * Method for retrieving customer orders, customer id is passed as a path variable
     * @param request
     * @return all orders of the requested customer
     */
    public Mono<ServerResponse> retrieveCustomerOrders(ServerRequest request) {
        Long userId = Long.parseLong(request.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        orderService.findCustomerOrders(userId),
                        Order.class
                );
    }
}
