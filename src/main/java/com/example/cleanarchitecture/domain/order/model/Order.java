package com.example.cleanarchitecture.domain.order.model;

import com.example.cleanarchitecture.core.domain.AbstractEntity;
import com.example.cleanarchitecture.core.exception.DomainValidationException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends AbstractEntity<Long> {
    private int quantity;
    private String customerInfo;
    private Long inventoryId;
    private String inventoryName;

    public Order(Long id, int quantity, String customerInfo, Long inventoryId) {
        this(id, quantity, customerInfo, inventoryId, null);
    }

    public Order(Long id, int quantity, String customerInfo, Long inventoryId, String inventoryName) {
        validateQuantity(quantity);
        validateCustomerInfo(customerInfo);
        this.setId(id);
        this.customerInfo = customerInfo;
        this.inventoryId = inventoryId;
        this.inventoryName = inventoryName;
    }

    private void validateCustomerInfo(String customerInfo) {
        if (customerInfo == null || customerInfo.trim().isEmpty()) {
            throw new DomainValidationException("Order customerInfo must not be null or empty.");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new DomainValidationException("Order quantity must be greater than or equal to 0.");
        }
    }
}
