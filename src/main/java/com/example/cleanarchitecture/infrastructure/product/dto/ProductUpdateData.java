package com.example.cleanarchitecture.infrastructure.product.dto;

import com.example.cleanarchitecture.infrastructure.product.validation.UniqueName;
import com.example.cleanarchitecture.usecase.product.dto.IProductUpdateData;

import jakarta.validation.constraints.Min;

public record ProductUpdateData(
        @UniqueName String name,
        @Min(value = 1L) Long price,
        Boolean enabled) implements IProductUpdateData {
}
