package com.example.webfluxwarehouses.service;

import com.example.webfluxwarehouses.entity.Warehouse;
import com.example.webfluxwarehouses.repository.WarehouseRepository;
import com.example.webfluxwarehouses.service.util.CoordinatesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service for warehouses
 */
@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final CoordinatesUtil coordinatesUtil;

    /**
     * Method which searches for closest warehouse with a required product
     * We get top 10 closest warehouses among which perform a search for the most suitable one
     *
     * @param xCoordinate x coordinate of customer
     * @param yCoordinate y coordinate of customer
     * @param productId   product which customer is willing to order
     * @return closest warehouse with required product
     */
    public Mono<Warehouse> findClosestWarehouse(Long xCoordinate, Long yCoordinate, Long productId) {
        return warehouseRepository.findClosestWarehouses(productId, xCoordinate, yCoordinate)
                .sort((firstWarehouse, secondWarehouse) ->
                        coordinatesUtil.compareDistanceToACoordinate(xCoordinate, yCoordinate,
                                firstWarehouse.getXCoordinate(), firstWarehouse.getYCoordinate(),
                                secondWarehouse.getXCoordinate(), secondWarehouse.getYCoordinate()))
                .last();
    }

    /**
     * Method for warehouse creation
     *
     * @param warehouse warehouse to create
     * @return created warehouse
     */
    public Mono<Warehouse> createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    /**
     * Method for retrieval of warehouses
     *
     * @return all warehouses
     */
    public Flux<Warehouse> findAllWarehouses() {
        return warehouseRepository.findAll();
    }

    /**
     * Method to retrieve warehouse by id
     *
     * @param id id of the warehouse
     * @return requested warehouse
     */
    public Mono<Warehouse> findWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }
}
