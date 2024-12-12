package com.example.cleanarchitecture.usecase.inventory;

import java.util.List;

import com.example.cleanarchitecture.domain.inventory.gateway.InventoryGateway;
import com.example.cleanarchitecture.domain.inventory.model.Inventory;
import com.example.cleanarchitecture.usecase.inventory.dto.IInventorySearchData;

public class SearchInventoryUseCase {
    private final InventoryGateway inventoryGateway;

    public SearchInventoryUseCase(InventoryGateway inventoryGateway) {
        this.inventoryGateway = inventoryGateway;
    }

    public List<Inventory> searchInventories(IInventorySearchData data) {
        return inventoryGateway.findAll(data.size(), data.page());
    }
}
