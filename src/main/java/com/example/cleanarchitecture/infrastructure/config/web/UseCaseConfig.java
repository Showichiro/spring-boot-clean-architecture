package com.example.cleanarchitecture.infrastructure.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.cleanarchitecture.infrastructure.inventory.gateway.InventoryDatabaseGateway;
import com.example.cleanarchitecture.infrastructure.order.gateway.OrderDatabaseGateway;
import com.example.cleanarchitecture.usecase.inventory.CreateInventoryUseCase;
import com.example.cleanarchitecture.usecase.inventory.DeleteInventoryUseCase;
import com.example.cleanarchitecture.usecase.inventory.GetInventoryUseCase;
import com.example.cleanarchitecture.usecase.inventory.SearchInventoryUseCase;
import com.example.cleanarchitecture.usecase.order.CreateOrderUseCase;

@Configuration
public class UseCaseConfig {

    // Inventory

    @Bean
    CreateInventoryUseCase createInventoryUseCase(InventoryDatabaseGateway inventoryDatabaseGateway) {
        return new CreateInventoryUseCase(inventoryDatabaseGateway);
    }

    @Bean
    GetInventoryUseCase getInventoryUseCase(InventoryDatabaseGateway inventoryDatabaseGateway) {
        return new GetInventoryUseCase(inventoryDatabaseGateway);
    }

    @Bean
    SearchInventoryUseCase searchInventoryUseCase(InventoryDatabaseGateway inventoryDatabaseGateway) {
        return new SearchInventoryUseCase(inventoryDatabaseGateway);
    }

    @Bean
    DeleteInventoryUseCase deleteInventoryUseCase(InventoryDatabaseGateway inventoryDatabaseGateway) {
        return new DeleteInventoryUseCase(inventoryDatabaseGateway);
    }

    // Order

    @Bean
    CreateOrderUseCase createOrderUseCase(OrderDatabaseGateway orderDatabaseGateway,
            InventoryDatabaseGateway inventoryDatabaseGateway) {
        return new CreateOrderUseCase(orderDatabaseGateway, inventoryDatabaseGateway);
    }
}
