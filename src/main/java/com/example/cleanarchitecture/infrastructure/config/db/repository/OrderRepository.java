package com.example.cleanarchitecture.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cleanarchitecture.infrastructure.config.db.schema.OrderSchema;

public interface OrderRepository extends JpaRepository<OrderSchema, Long> {

}
