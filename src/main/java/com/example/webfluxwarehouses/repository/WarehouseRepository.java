package com.example.webfluxwarehouses.repository;

import com.example.webfluxwarehouses.entity.Warehouse;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Repository class for warehouses
 */
@Repository
public interface WarehouseRepository extends ReactiveCrudRepository<Warehouse, Long> {
    /**
     * The method which searches for the closest warehouse with the required product
     * we find top 10 closest by coordinates module warehouse
     *
     * @param productId   id of the requested product
     * @param xCoordinate x coordinate of the customer
     * @param yCoordinate y coordinate of the customer
     * @return the closest warehouse with required product
     */
    @Query("select * from warehouse join warehouse_product on warehouse.id = warehouse_product.warehouse_id " +
            "where warehouse_product.product_id = :productId " +
            "order by (abs(warehouse.x_coordinate - :xCoordinate) + abs(warehouse.y_coordinate - :yCoordinate)) limit 10")
    Flux<Warehouse> findClosestWarehouses(@Param("productId") Long productId,
                                          @Param("xCoordinate") Long xCoordinate,
                                          @Param("yCoordinate") Long yCoordinate

    );
}
