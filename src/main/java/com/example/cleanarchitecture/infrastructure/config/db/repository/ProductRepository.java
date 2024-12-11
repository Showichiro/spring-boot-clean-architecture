package com.example.cleanarchitecture.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cleanarchitecture.infrastructure.config.db.schema.ProductSchema;

public interface ProductRepository extends JpaRepository<ProductSchema, Long> {
    boolean existsByName(String name);
}
