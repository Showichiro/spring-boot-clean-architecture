package com.example.cleanarchitecture.infrastructure.product.dto;

import com.example.cleanarchitecture.domain.product.model.Product;
import com.example.cleanarchitecture.usecase.product.dto.IProductPublicData;

public record ProductPublicData(
        Long id,
        String name,
        Long price,
        boolean enabled) implements IProductPublicData {
    private ProductPublicData(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.isEnabled());
    }

    public static ProductPublicData fromProduct(Product product) {
        return new ProductPublicData(product);
    }
}
