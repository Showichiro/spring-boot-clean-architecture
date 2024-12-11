package com.example.cleanarchitecture.core.product.exception;

import com.example.cleanarchitecture.core.exception.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(Long id) {
        super("product-" + id + " was not found");
    }
}
