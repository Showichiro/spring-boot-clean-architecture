package com.example.cleanarchitecture.usecase.inventory;

import com.example.cleanarchitecture.domain.inventory.gateway.InventoryGateway;
import com.example.cleanarchitecture.domain.inventory.model.Inventory;
import com.example.cleanarchitecture.usecase.inventory.dto.IInventoryRegistrationData;

public class CreateInventoryUseCase {
    private final InventoryGateway inventoryGateway;

    public CreateInventoryUseCase(InventoryGateway inventoryGateway) {
        this.inventoryGateway = inventoryGateway;
    }

    public Inventory createInventory(IInventoryRegistrationData data) {
        Inventory inventory = new Inventory(null, data.name(), data.quantity());
        return inventoryGateway.create(inventory);
    }
}
