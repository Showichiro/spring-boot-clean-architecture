package com.example.cleanarchitecture.infrastructure.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.cleanarchitecture.infrastructure.product.gateway.ProductDatabaseGateway;
import com.example.cleanarchitecture.usecase.product.CreateProductUseCase;
import com.example.cleanarchitecture.usecase.product.GetProductUseCase;
import com.example.cleanarchitecture.usecase.product.UpdateProductUseCase;

@Configuration
public class UseCaseConfig {
    @Bean
    CreateProductUseCase createProductUseCase(ProductDatabaseGateway productDatabaseGateway) {
        return new CreateProductUseCase(productDatabaseGateway);
    }

    @Bean
    UpdateProductUseCase updateProductUseCase(ProductDatabaseGateway productDatabaseGateway) {
        return new UpdateProductUseCase(productDatabaseGateway);
    }

    @Bean
    GetProductUseCase getProductUseCase(ProductDatabaseGateway productDatabaseGateway) {
        return new GetProductUseCase(productDatabaseGateway);
    }
}
