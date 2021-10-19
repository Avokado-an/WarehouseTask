package com.example.webfluxwarehouses.repository;

import com.example.webfluxwarehouses.entity.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Repository class for product
 */
@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    /**
     * method for retrieval of products contained in the warehouse
     * @param warehouseId id of the warehouse
     * @return all products contained in the warehouse
     */
    @Query("select * from product " +
            "join warehouse_product on product.id = warehouse_product.product_id " +
            "where warehouse_product.warehouse_id=:warehouseId")
    Flux<Product> findWarehouseProducts(@Param("warehouseId") Long warehouseId);
}
