package com.example.cleanarchitecture.infrastructure.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanarchitecture.domain.product.exception.ProductNotFoundException;
import com.example.cleanarchitecture.infrastructure.product.dto.ProductPublicData;
import com.example.cleanarchitecture.usecase.product.GetProductUseCase;

@RestController
public class GetProductController {
    @Autowired
    private GetProductUseCase getProductUseCase;

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductPublicData getProduct(@PathVariable Long id) throws ProductNotFoundException {
        return ProductPublicData.fromProduct(getProductUseCase.execute(id));
    }
}
