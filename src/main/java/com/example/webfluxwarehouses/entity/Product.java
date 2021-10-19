package com.example.webfluxwarehouses.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity class for products. Stored as a table named "product"
 */
@Data
@NoArgsConstructor
@Table("product")
public class Product {
    @Id
    private Long id;
    private String productName;
}
