package com.example.webfluxwarehouses.router;

import com.example.webfluxwarehouses.handler.WarehouseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

/**
 * Class which is responsible for defining mapping for our server and nothing more
 */
@Configuration
public class WarehouseRouter {
    @Bean
    public RouterFunction<ServerResponse> getAllWarehouses(WarehouseHandler handler) {
        RequestPredicate allWarehousesRoute = RequestPredicates
                .GET("/warehouses")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        return RouterFunctions
                .route(allWarehousesRoute, handler::findAllWarehouses);
    }

    @Bean
    public RouterFunction<ServerResponse> getWarehouseById(WarehouseHandler handler) {
        RequestPredicate warehouseByIdRoute = RequestPredicates
                .GET("/warehouses/{id}")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        return RouterFunctions
                .route(warehouseByIdRoute, handler::findWarehouseById);

    }

    @Bean
    public RouterFunction<ServerResponse> createWarehouse(WarehouseHandler handler) {
        RequestPredicate saveWarehouseRoute = RequestPredicates
                .POST("/warehouses")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        return RouterFunctions.route(saveWarehouseRoute, handler::createWarehouse);
    }

    @Bean
    public RouterFunction<ServerResponse> findWarehouseProducts(WarehouseHandler handler) {
        RequestPredicate saveWarehouseRoute = RequestPredicates
                .GET("/warehouses/{id}/products")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        return RouterFunctions.route(saveWarehouseRoute, handler::findWarehouseProducts);
    }
}
