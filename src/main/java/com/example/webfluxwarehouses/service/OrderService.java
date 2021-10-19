package com.example.webfluxwarehouses.service;

import com.example.webfluxwarehouses.entity.Customer;
import com.example.webfluxwarehouses.entity.Order;
import com.example.webfluxwarehouses.entity.Warehouse;
import com.example.webfluxwarehouses.repository.OrderRepository;
import com.example.webfluxwarehouses.service.util.CoordinatesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Service class for orders
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final CoordinatesUtil coordinatesUtil;
    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    private final WarehouseService warehouseService;

    /**
     * Method which is responsible for order creation
     * Order is created according to following rules:
     *  - specified warehouse must have the required for order product
     *  - we need to find the closest warehouse
     * @param customerId id of the customer who created the order
     * @param order the order itself
     * @return the created order
     */
    public Mono<Order> createOrder(Long customerId, Order order) {
        order.setCreationDate(LocalDateTime.now());
        order.setCustomerId(customerId);
        return customerService.findById(customerId).flatMap(customer ->
                findClosestWarehouse(customer.getXCoordinate(), customer.getYCoordinate(), order.getProductId())
                        .flatMap(warehouse -> defineOrderDetails(order, customer, warehouse))
        );
    }

    /**
     * method for retrieval of customer orders
     * @param customerId id of the customer
     * @return orders of the customer
     */
    public Flux<Order> findCustomerOrders(Long customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

    /**
     * @param xCoordinate x coordinate of the customer
     * @param yCoordinate y coordinate of the customer
     * @param productId requested product
     * @return the closest warehouse with required product
     */
    private Mono<Warehouse> findClosestWarehouse(Long xCoordinate, Long yCoordinate, Long productId) {
        return warehouseService.findClosestWarehouse(xCoordinate, yCoordinate, productId);
    }

    /**
     * Method which is responsible for specifying order details
     * Like distance to warehouse, warehouse id, etc
     * @param order order which requires additional details
     * @param customer customer of the order
     * @param warehouse warehouse from which order is coming
     * @return final version of the order
     */
    private Mono<Order> defineOrderDetails(Order order, Customer customer, Warehouse warehouse) {
        order.setWarehouseId(warehouse.getId());
        long distance = coordinatesUtil.calculateDistance(
                customer.getXCoordinate(), customer.getYCoordinate(),
                warehouse.getXCoordinate(), warehouse.getYCoordinate()
        );
        order.setDistance(distance);
        return orderRepository.save(order);
    }
}
