package com.example.cleanarchitecture.usecase.inventory;

import com.example.cleanarchitecture.domain.inventory.exception.InventoryNotFoundException;
import com.example.cleanarchitecture.domain.inventory.gateway.InventoryGateway;
import com.example.cleanarchitecture.domain.inventory.model.Inventory;

public class GetInventoryUseCase {
    private final InventoryGateway inventoryGateway;

    public GetInventoryUseCase(InventoryGateway inventoryGateway) {
        this.inventoryGateway = inventoryGateway;
    }

    public Inventory getInventory(Long id) throws InventoryNotFoundException {
        return inventoryGateway.findById(id).orElseThrow(() -> new InventoryNotFoundException(id));
    }
}
