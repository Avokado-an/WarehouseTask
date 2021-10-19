package com.example.webfluxwarehouses.handler;

import com.example.webfluxwarehouses.entity.Product;
import com.example.webfluxwarehouses.entity.Warehouse;
import com.example.webfluxwarehouses.service.ProductService;
import com.example.webfluxwarehouses.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Controller class for warehouses
 */
@Configuration
@RequiredArgsConstructor
public class WarehouseHandler {
    private final WarehouseService warehouseService;
    private final ProductService productService;

    /**
     * Simple method for warehouse creation, warehouse data is passed in request body
     * @param request
     * @return created warehouse
     */
    public Mono<ServerResponse> createWarehouse(ServerRequest request) {
        Mono<Warehouse> warehouseToCreate = request.bodyToMono(Warehouse.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        warehouseToCreate.flatMap(warehouseService::createWarehouse),
                        Warehouse.class
                );
    }

    /**
     * Simple method to retrieve all warehouses
     * @param request
     * @return all warehouses
     */
    public Mono<ServerResponse> findAllWarehouses(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        warehouseService.findAllWarehouses(),
                        Warehouse.class
                );
    }

    /**
     * Method to retrieve warehouse by id, which is passed as a path variable
     * @param request
     * @return requested warehouse
     */
    public Mono<ServerResponse> findWarehouseById(ServerRequest request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        warehouseService.findWarehouseById(id),
                        Warehouse.class
                );
    }

    /**
     * Method to retrieve all products from the warehouse
     * @param request
     * @return all products of the requested warehouse
     */
    public Mono<ServerResponse> findWarehouseProducts(ServerRequest request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        productService.findWarehouseProducts(id),
                        Product.class
                );
    }
}
