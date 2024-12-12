package com.example.cleanarchitecture.infrastructure.inventory.dto;

import com.example.cleanarchitecture.domain.inventory.model.Inventory;
import com.example.cleanarchitecture.usecase.inventory.dto.IInventoryPublicData;

public record InventoryPublicData(
        Long id,
        String name,
        int quantity) implements IInventoryPublicData {
    private InventoryPublicData(Inventory inventory) {
        this(inventory.getId(), inventory.getName(), inventory.getQuantity());
    }

    public static InventoryPublicData fromInventory(Inventory inventory) {
        return new InventoryPublicData(inventory);
    }
}
