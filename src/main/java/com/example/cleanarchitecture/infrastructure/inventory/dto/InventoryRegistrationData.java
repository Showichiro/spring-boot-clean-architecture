package com.example.cleanarchitecture.infrastructure.inventory.dto;

import com.example.cleanarchitecture.infrastructure.inventory.validation.UniqueName;
import com.example.cleanarchitecture.usecase.inventory.dto.IInventoryRegistrationData;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record InventoryRegistrationData(
        @NotBlank @UniqueName String name,
        @Min(0) int quantity) implements IInventoryRegistrationData {
}
