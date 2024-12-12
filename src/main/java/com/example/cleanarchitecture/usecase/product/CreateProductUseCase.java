package com.example.cleanarchitecture.usecase.product;

import com.example.cleanarchitecture.domain.product.gateway.ProductGateway;
import com.example.cleanarchitecture.domain.product.model.Product;
import com.example.cleanarchitecture.usecase.product.dto.IProductRegistrationData;

public class CreateProductUseCase {
    private final ProductGateway productGateway;

    public CreateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product execute(IProductRegistrationData data) {
        Product product = new Product(data.name(), data.price(), data.enabled());
        return productGateway.create(product);
    }
}
