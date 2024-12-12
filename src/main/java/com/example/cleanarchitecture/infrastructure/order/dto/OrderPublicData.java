package com.example.cleanarchitecture.infrastructure.order.dto;

import com.example.cleanarchitecture.domain.order.model.Order;
import com.example.cleanarchitecture.usecase.order.dto.IOrderPublicData;

public record OrderPublicData(
        Long id,
        long quantity,
        String customerInfo,
        Long inventoryId,
        String inventoryName)
        implements IOrderPublicData {
    private OrderPublicData(Order order) {
        this(order.getId(), order.getQuantity(), order.getCustomerInfo(), order.getInventoryId(),
                order.getInventoryName());
    }

    public static OrderPublicData fromOrder(Order order) {
        return new OrderPublicData(order);
    }
}
