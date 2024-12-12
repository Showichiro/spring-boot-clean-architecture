package com.example.cleanarchitecture.infrastructure.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanarchitecture.usecase.inventory.DeleteInventoryUseCase;

@RestController
public class DeleteInventoryController {
    @Autowired
    private DeleteInventoryUseCase deleteInventoryUseCase;

    @DeleteMapping("/inventories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInventory(@PathVariable Long id) {
        deleteInventoryUseCase.deleteInventory(id);
    }
}
