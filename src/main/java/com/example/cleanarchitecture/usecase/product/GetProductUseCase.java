package com.example.cleanarchitecture.usecase.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cleanarchitecture.core.product.exception.ProductNotFoundException;
import com.example.cleanarchitecture.core.product.gateway.ProductGateway;
import com.example.cleanarchitecture.core.product.model.Product;

@Component
public class GetProductUseCase {
    @Autowired
    private ProductGateway productGateway;

    public Product execute(Long id) throws ProductNotFoundException {
        return productGateway.findById(id).orElseThrow(ProductNotFoundException::new);
    }
}
