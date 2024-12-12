package com.example.cleanarchitecture.domain.inventory.exception;

import com.example.cleanarchitecture.core.exception.NotFoundException;

public class InventoryNotFoundException extends NotFoundException {
    public InventoryNotFoundException(Long id) {
        super("inventory-" + id + " is not found");
    }
}
