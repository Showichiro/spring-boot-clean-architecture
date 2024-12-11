package com.example.cleanarchitecture.usecase.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cleanarchitecture.core.product.gateway.ProductGateway;
import com.example.cleanarchitecture.core.product.model.Product;
import com.example.cleanarchitecture.usecase.product.dto.IProductRegistrationData;

@Component
public class CreateProductUseCase {
    @Autowired
    private ProductGateway productGateway;

    public Product execute(IProductRegistrationData data) {
        Product product = new Product(data.name(), data.price(), data.enabled());
        return productGateway.create(product);
    }
}
