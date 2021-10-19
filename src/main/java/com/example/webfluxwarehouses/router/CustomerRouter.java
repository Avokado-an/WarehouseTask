package com.example.webfluxwarehouses.router;

import com.example.webfluxwarehouses.handler.CustomerHandler;
import com.example.webfluxwarehouses.handler.OrderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

/**
 * Class which is responsible for defining mapping for our server and nothing more
 */
@Configuration
public class CustomerRouter {
    @Bean
    public RouterFunction<ServerResponse> getAllCustomers(CustomerHandler handler) {
        RequestPredicate allMessagesRoute = RequestPredicates
                .GET("/users")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        return RouterFunctions
                .route(allMessagesRoute, handler::retrieveAllCustomers);
    }

    @Bean
    public RouterFunction<ServerResponse> getCustomersById(CustomerHandler handler) {
        RequestPredicate messageByKeyRoute = RequestPredicates
                .GET("/users/{id}")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        return RouterFunctions
                .route(messageByKeyRoute, handler::retrieveCustomerById);

    }

    @Bean
    public RouterFunction<ServerResponse> createCustomers(CustomerHandler handler) {
        RequestPredicate saveMessageRoute = RequestPredicates
                .POST("/users")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        return RouterFunctions.route(saveMessageRoute, handler::createCustomer);
    }

    @Bean
    public RouterFunction<ServerResponse> createOrder(OrderHandler handler) {
        RequestPredicate saveMessageRoute = RequestPredicates
                .POST("/users/{id}/order")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        return RouterFunctions.route(saveMessageRoute, handler::createOrder);
    }

    @Bean
    public RouterFunction<ServerResponse> showUserOrders(OrderHandler handler) {
        RequestPredicate saveMessageRoute = RequestPredicates
                .GET("/users/{id}/order")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        return RouterFunctions.route(saveMessageRoute, handler::retrieveCustomerOrders);
    }
}
