package com.example.cleanarchitecture.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cleanarchitecture.infrastructure.config.db.schema.InventorySchema;

public interface InventoryRepository extends JpaRepository<InventorySchema, Long> {
    boolean existsByName(String name);
}
