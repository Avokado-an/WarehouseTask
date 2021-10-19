package com.example.webfluxwarehouses.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity class for customers. Stored as a table named "customer"
 */
@Data
@NoArgsConstructor
@Table("customer")
public class Customer {
    @Id
    private Long id;
    private String name;
    private Long xCoordinate;
    private Long yCoordinate;
}
