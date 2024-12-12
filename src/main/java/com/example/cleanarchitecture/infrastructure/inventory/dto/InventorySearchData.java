package com.example.cleanarchitecture.infrastructure.inventory.dto;

import com.example.cleanarchitecture.usecase.inventory.dto.IInventorySearchData;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record InventorySearchData(
        @Min(1) @Max(100) int size,
        @Min(0) int page) implements IInventorySearchData {
}
