package com.example.cleanarchitecture.domain.inventory.exception;

import com.example.cleanarchitecture.core.exception.BadRequestException;

public class InventoryIrreducibleException extends BadRequestException {
    public InventoryIrreducibleException() {
        super("this inventory is not reducible");
    }
}
