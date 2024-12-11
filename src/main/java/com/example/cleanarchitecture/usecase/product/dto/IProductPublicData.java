package com.example.cleanarchitecture.usecase.product.dto;

public interface IProductPublicData {
    Long id();

    String name();

    Long price();

    boolean enabled();
}
