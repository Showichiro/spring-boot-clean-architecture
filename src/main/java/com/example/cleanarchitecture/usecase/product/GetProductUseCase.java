package com.example.cleanarchitecture.usecase.product;

import com.example.cleanarchitecture.core.product.exception.ProductNotFoundException;
import com.example.cleanarchitecture.core.product.gateway.ProductGateway;
import com.example.cleanarchitecture.core.product.model.Product;

public class GetProductUseCase {
    private final ProductGateway productGateway;

    public GetProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product execute(Long id) throws ProductNotFoundException {
        return productGateway.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }
}
