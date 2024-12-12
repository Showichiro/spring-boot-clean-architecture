package com.example.cleanarchitecture.infrastructure.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanarchitecture.infrastructure.inventory.dto.InventoryPublicData;
import com.example.cleanarchitecture.infrastructure.inventory.dto.InventoryRegistrationData;
import com.example.cleanarchitecture.usecase.inventory.CreateInventoryUseCase;

import jakarta.validation.Valid;

@RestController
public class CreateInventoryController {
    @Autowired
    private CreateInventoryUseCase createInventoryUseCase;

    @PostMapping("/inventories")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryPublicData createInventory(@Valid @RequestBody InventoryRegistrationData data) {
        return InventoryPublicData.fromInventory(createInventoryUseCase.createInventory(data));
    }
}
