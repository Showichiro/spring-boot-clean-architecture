package com.example.cleanarchitecture.infrastructure.order.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cleanarchitecture.domain.order.gateway.OrderGateway;
import com.example.cleanarchitecture.domain.order.model.Order;
import com.example.cleanarchitecture.infrastructure.config.db.mapper.OrderMapper;
import com.example.cleanarchitecture.infrastructure.config.db.repository.InventoryRepository;
import com.example.cleanarchitecture.infrastructure.config.db.repository.OrderRepository;
import com.example.cleanarchitecture.infrastructure.config.db.schema.InventorySchema;
import com.example.cleanarchitecture.infrastructure.config.db.schema.OrderSchema;

@Component
public class OrderDatabaseGateway implements OrderGateway {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Order create(Order entity) {
        InventorySchema inventorySchema = inventoryRepository.findById(entity.getInventoryId()).orElseThrow();
        OrderSchema orderSchema = OrderMapper.fromOrder(entity);
        orderSchema.setInventorySchema(inventorySchema);
        return OrderMapper.toOrder(orderRepository.save(orderSchema));
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Order> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Order update(Order product) {
        // TODO Auto-generated method stub
        return null;
    }
}
