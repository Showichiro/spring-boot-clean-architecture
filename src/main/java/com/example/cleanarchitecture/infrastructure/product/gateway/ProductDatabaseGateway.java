package com.example.cleanarchitecture.infrastructure.product.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cleanarchitecture.core.product.gateway.ProductGateway;
import com.example.cleanarchitecture.core.product.model.Product;
import com.example.cleanarchitecture.infrastructure.config.db.repository.ProductRepository;
import com.example.cleanarchitecture.infrastructure.config.db.schema.ProductSchema;

@Component
public class ProductDatabaseGateway implements ProductGateway {

    @Autowired
    private ProductRepository productRepository;

    private Product save(Product product) {
        return productRepository.save(ProductSchema.fromProduct(product)).toProduct();
    }

    @Override
    public Product create(Product product) {
        return this.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream().map(ProductSchema::toProduct).toList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(ProductSchema::toProduct);
    }

    @Override
    public Product update(Product product) {
        return this.save(product);
    }
}
