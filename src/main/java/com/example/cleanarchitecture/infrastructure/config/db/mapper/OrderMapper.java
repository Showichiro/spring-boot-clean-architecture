package com.example.cleanarchitecture.infrastructure.config.db.mapper;

import com.example.cleanarchitecture.domain.order.model.Order;
import com.example.cleanarchitecture.infrastructure.config.db.schema.OrderSchema;

public class OrderMapper {
    private OrderMapper() {
    }

    public static OrderSchema fromOrder(Order order) {
        return new OrderSchema(order);
    }

    public static Order toOrder(OrderSchema orderSchema) {
        return new Order(orderSchema.getId(),
                orderSchema.getQuantity(),
                orderSchema.getCustomerInfo(),
                orderSchema.getInventorySchema().getId(),
                orderSchema.getInventorySchema().getName());
    }
}
