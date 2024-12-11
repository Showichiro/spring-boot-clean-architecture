package com.example.cleanarchitecture.usecase.product;

import com.example.cleanarchitecture.core.product.exception.ProductNotFoundException;
import com.example.cleanarchitecture.core.product.gateway.ProductGateway;
import com.example.cleanarchitecture.core.product.model.Product;
import com.example.cleanarchitecture.usecase.product.dto.IProductUpdateData;

public class UpdateProductUseCase {
    private final ProductGateway productGateway;

    public UpdateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product execute(Long id, IProductUpdateData data) throws ProductNotFoundException {
        Product product = productGateway.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
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
