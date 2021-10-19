package com.example.webfluxwarehouses.service;

import com.example.webfluxwarehouses.entity.Product;
import com.example.webfluxwarehouses.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Service class for products
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * find all products in warehouse
     *
     * @param warehouseId id of the warehouse
     * @return products which are present in the chosen warehouse
     */
    public Flux<Product> findWarehouseProducts(Long warehouseId) {
        return productRepository.findWarehouseProducts(warehouseId);
    }
}
