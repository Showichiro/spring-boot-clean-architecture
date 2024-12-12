package com.example.cleanarchitecture.infrastructure.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanarchitecture.application.order.CreateOrderService;
import com.example.cleanarchitecture.domain.inventory.exception.InventoryIrreducibleException;
import com.example.cleanarchitecture.domain.inventory.exception.InventoryNotFoundException;
import com.example.cleanarchitecture.infrastructure.order.dto.OrderPublicData;
import com.example.cleanarchitecture.infrastructure.order.dto.OrderRegistrationData;

import jakarta.validation.Valid;

@RestController
public class CreateOrderController {
    @Autowired
    private CreateOrderService createOrderService;

    @PostMapping("inventories/{inventoryId}/orders")
    public OrderPublicData createOrder(@PathVariable Long inventoryId, @Valid @RequestBody OrderRegistrationData data)
            throws InventoryNotFoundException, InventoryIrreducibleException {
        return OrderPublicData.fromOrder(createOrderService.createOrder(inventoryId, data));
    }

}
