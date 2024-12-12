package com.example.cleanarchitecture.infrastructure.order.dto;

import com.example.cleanarchitecture.usecase.order.dto.IOrderRegistrationData;

public record OrderRegistrationData(
        int quantity,
        String customerInfo) implements IOrderRegistrationData {
}
