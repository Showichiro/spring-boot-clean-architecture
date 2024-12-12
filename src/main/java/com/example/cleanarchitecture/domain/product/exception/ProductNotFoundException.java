package com.example.cleanarchitecture.domain.product.exception;

import com.example.cleanarchitecture.core.exception.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(Long id) {
        super("product-" + id + " was not found");
    }
}
