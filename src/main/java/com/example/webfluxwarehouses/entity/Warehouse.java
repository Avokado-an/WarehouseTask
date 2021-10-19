package com.example.webfluxwarehouses.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity class for warehouses. Stored as a table named "warehouse"
 */
@Data
@NoArgsConstructor
@Table("warehouse")
public class Warehouse {
    @Id
    private Long id;
    private Long xCoordinate;
    private Long yCoordinate;
}
