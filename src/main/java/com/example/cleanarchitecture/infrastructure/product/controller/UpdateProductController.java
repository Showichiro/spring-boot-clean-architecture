package com.example.cleanarchitecture.infrastructure.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanarchitecture.core.product.exception.AdminNotFoundException;
import com.example.cleanarchitecture.infrastructure.product.dto.ProductPublicData;
import com.example.cleanarchitecture.infrastructure.product.dto.ProductUpdateData;
import com.example.cleanarchitecture.usecase.product.UpdateProductUseCase;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

@RestController
public class UpdateProductController {
    @Autowired
    private UpdateProductUseCase updateProductUseCase;

    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductPublicData updateProduct(@PathVariable Long id, @Valid @RequestBody ProductUpdateData data)
            throws AdminNotFoundException {
        return ProductPublicData.fromProduct(updateProductUseCase.execute(id, data));
    }
}
