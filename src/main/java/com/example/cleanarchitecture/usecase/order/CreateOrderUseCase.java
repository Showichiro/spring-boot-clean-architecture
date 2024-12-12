package com.example.cleanarchitecture.usecase.order;

import com.example.cleanarchitecture.domain.inventory.exception.InventoryIrreducibleException;
import com.example.cleanarchitecture.domain.inventory.exception.InventoryNotFoundException;
import com.example.cleanarchitecture.domain.inventory.gateway.InventoryGateway;
import com.example.cleanarchitecture.domain.inventory.model.Inventory;
import com.example.cleanarchitecture.domain.order.gateway.OrderGateway;
import com.example.cleanarchitecture.domain.order.model.Order;
import com.example.cleanarchitecture.usecase.order.dto.IOrderRegistrationData;

public class CreateOrderUseCase {
    private final OrderGateway orderGateway;
    private final InventoryGateway inventoryGateway;

    public CreateOrderUseCase(OrderGateway orderGateway, InventoryGateway inventoryGateway) {
        this.orderGateway = orderGateway;
        this.inventoryGateway = inventoryGateway;
    }

    public Order createOrder(Long inventoryId, IOrderRegistrationData data)
            throws InventoryNotFoundException, InventoryIrreducibleException {
        Inventory inventory = inventoryGateway.findById(inventoryId)
                // 在庫がない時点でエラー
                .orElseThrow(() -> new InventoryNotFoundException(inventoryId));
        // 在庫を減らすことができない場合、エラー終了.
        if (!inventory.reducible(data.quantity())) {
            throw new InventoryIrreducibleException();
        }
        // 在庫を減らす.
        Inventory reduced = inventory.reduce(data.quantity());
        Inventory updated = inventoryGateway.update(reduced);
        // 注文を登録する.
        Order order = new Order(null, data.quantity(), data.customerInfo(), updated.getId());
        return orderGateway.create(order);
    }
}
