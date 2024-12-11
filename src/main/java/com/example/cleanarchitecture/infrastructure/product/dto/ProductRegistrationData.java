package com.example.cleanarchitecture.infrastructure.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.example.cleanarchitecture.infrastructure.product.validation.UniqueName;
import com.example.cleanarchitecture.usecase.product.dto.IProductRegistrationData;

public record ProductRegistrationData(@NotBlank @UniqueName String name,
        @NotNull @Min(value = 1L) Long price,
        boolean enabled) implements IProductRegistrationData {
}
