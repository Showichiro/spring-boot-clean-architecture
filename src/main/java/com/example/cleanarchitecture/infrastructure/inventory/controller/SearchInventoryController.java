package com.example.cleanarchitecture.infrastructure.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanarchitecture.infrastructure.inventory.dto.InventoryPublicData;
import com.example.cleanarchitecture.infrastructure.inventory.dto.InventorySearchData;
import com.example.cleanarchitecture.usecase.inventory.SearchInventoryUseCase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
public class SearchInventoryController {
    @Autowired
    private SearchInventoryUseCase searchInventoryUseCase;

    @GetMapping("/inventories")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryPublicData> searchInventoryPublicData(
            @Valid @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) @Max(100) int size,
            @RequestParam(name = "page", required = false, defaultValue = "0") @Min(0) int page) {
        InventorySearchData data = new InventorySearchData(size, page);
        return searchInventoryUseCase.searchInventories(data)
                .stream()
                .map(InventoryPublicData::fromInventory)
                .toList();
    }
}
