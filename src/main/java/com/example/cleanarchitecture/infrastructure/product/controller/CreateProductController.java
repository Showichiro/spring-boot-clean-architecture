package com.example.cleanarchitecture.infrastructure.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanarchitecture.infrastructure.product.dto.ProductPublicData;
import com.example.cleanarchitecture.infrastructure.product.dto.ProductRegistrationData;
import com.example.cleanarchitecture.usecase.product.CreateProductUseCase;

import jakarta.validation.Valid;

@RestController
public class CreateProductController {
    @Autowired
    private CreateProductUseCase createProductUseCase;

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductPublicData createProduct(@Valid @RequestBody ProductRegistrationData data) {
        return ProductPublicData.fromProduct(createProductUseCase.execute(data));
    }
}
