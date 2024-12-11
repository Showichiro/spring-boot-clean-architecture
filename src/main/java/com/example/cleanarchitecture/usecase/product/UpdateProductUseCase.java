package com.example.cleanarchitecture.usecase.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cleanarchitecture.core.product.exception.ProductNotFoundException;
import com.example.cleanarchitecture.core.product.gateway.ProductGateway;
import com.example.cleanarchitecture.core.product.model.Product;
import com.example.cleanarchitecture.usecase.product.dto.IProductUpdateData;

@Component
public class UpdateProductUseCase {
    @Autowired
    private ProductGateway productGateway;

    public Product execute(Long id, IProductUpdateData data) throws ProductNotFoundException {
        Product product = productGateway.findById(id).orElseThrow(ProductNotFoundException::new);
        if (data.name() != null && !data.name().isBlank()) {
            product.setName(data.name());
        }
        if (data.price() != null) {
            product.setPrice(data.price());
        }
        if (data.enabled() != null) {
            product.setEnabled(data.enabled());
        }
        return productGateway.update(product);
    }
}
