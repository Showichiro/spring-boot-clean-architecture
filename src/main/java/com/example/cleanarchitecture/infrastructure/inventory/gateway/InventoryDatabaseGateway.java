package com.example.cleanarchitecture.infrastructure.inventory.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.example.cleanarchitecture.domain.inventory.gateway.InventoryGateway;
import com.example.cleanarchitecture.domain.inventory.model.Inventory;
import com.example.cleanarchitecture.infrastructure.config.db.mapper.InventoryMapper;
import com.example.cleanarchitecture.infrastructure.config.db.repository.InventoryRepository;

@Component
public class InventoryDatabaseGateway implements InventoryGateway {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory create(Inventory product) {
        return InventoryMapper.toInventory(inventoryRepository.save(InventoryMapper.fromInventory(product)));
    }

    @Override
    public void delete(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll().stream().map(InventoryMapper::toInventory).toList();
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id).map(InventoryMapper::toInventory);
    }

    @Override
    public Inventory update(Inventory product) {
        return InventoryMapper.toInventory(inventoryRepository.save(InventoryMapper.fromInventory(product)));
    }

    @Override
    public List<Inventory> findAll(int size, int page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return inventoryRepository.findAll(pageRequest).getContent()
                .stream()
                .map(InventoryMapper::toInventory)
                .toList();
    }
}
