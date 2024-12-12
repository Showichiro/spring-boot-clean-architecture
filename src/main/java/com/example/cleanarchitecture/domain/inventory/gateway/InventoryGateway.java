package com.example.cleanarchitecture.domain.inventory.gateway;

import java.util.List;

import com.example.cleanarchitecture.core.domain.IGateway;
import com.example.cleanarchitecture.domain.inventory.model.Inventory;

public interface InventoryGateway extends IGateway<Inventory, Long> {
    List<Inventory> findAll(int size, int page);
}
