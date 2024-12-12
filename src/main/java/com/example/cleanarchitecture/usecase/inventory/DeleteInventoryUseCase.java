package com.example.cleanarchitecture.usecase.inventory;

import com.example.cleanarchitecture.domain.inventory.gateway.InventoryGateway;

public class DeleteInventoryUseCase {

    private final InventoryGateway inventoryGateway;

    public DeleteInventoryUseCase(InventoryGateway inventoryGateway) {
        this.inventoryGateway = inventoryGateway;
    }

    public void deleteInventory(Long id) {
        inventoryGateway.delete(id);
    }
}
