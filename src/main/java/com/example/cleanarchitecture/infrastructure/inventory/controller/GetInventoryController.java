package com.example.cleanarchitecture.infrastructure.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanarchitecture.domain.inventory.exception.InventoryNotFoundException;
import com.example.cleanarchitecture.infrastructure.inventory.dto.InventoryPublicData;
import com.example.cleanarchitecture.usecase.inventory.GetInventoryUseCase;

@RestController
public class GetInventoryController {
    @Autowired
    private GetInventoryUseCase getInventoryUseCase;

    @GetMapping("inventories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryPublicData getInventory(@PathVariable Long id) throws InventoryNotFoundException {
        return InventoryPublicData.fromInventory(getInventoryUseCase.getInventory(id));
    }
}
