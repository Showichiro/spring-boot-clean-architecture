package com.example.cleanarchitecture.infrastructure.config.db.mapper;

import com.example.cleanarchitecture.domain.inventory.model.Inventory;
import com.example.cleanarchitecture.infrastructure.config.db.schema.InventorySchema;

public class InventoryMapper {
    private InventoryMapper() {
    }

    public static InventorySchema fromInventory(Inventory inventory) {
        return new InventorySchema(inventory);
    }

    public static Inventory toInventory(InventorySchema schema) {
        var inventory = new Inventory(schema.getId(), schema.getName(), schema.getQuantity());
        return inventory;
    }
}
