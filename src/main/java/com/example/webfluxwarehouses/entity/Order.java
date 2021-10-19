package com.example.webfluxwarehouses.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Entity class for orders. Stored as a table named "customer_order"
 */
@Data
@NoArgsConstructor
@Table("customer_order")
public class Order {
    @Id
    private Long id;
    private Long customerId;
    private Long warehouseId;
    private Long distance;
    private LocalDateTime creationDate;
    private Long productId;
}
