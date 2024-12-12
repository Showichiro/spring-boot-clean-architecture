package com.example.cleanarchitecture.usecase.order.dto;

public interface IOrderPublicData {
    Long id();

    long quantity();

    String customerInfo();

    Long inventoryId();

    String inventoryName();
}
