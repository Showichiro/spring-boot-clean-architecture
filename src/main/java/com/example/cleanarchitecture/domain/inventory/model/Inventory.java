package com.example.cleanarchitecture.domain.inventory.model;

import com.example.cleanarchitecture.core.domain.AbstractEntity;
import com.example.cleanarchitecture.core.exception.DomainValidationException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Inventory extends AbstractEntity<Long> {
    private String name;
    private int quantity;

    public Inventory(Long id, String name, int quantity) {
        validateName(name);
        validateQuantity(quantity);

        super.setId(id);
        this.name = name;
        this.quantity = quantity;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new DomainValidationException("Inventory name must not be null or empty.");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new DomainValidationException("Inventory quantity must be greater than or equal to 0.");
        }
    }

    /** 在庫を減らすことが可能かどうか. */
    public boolean reducible(int amount) {
        return this.getQuantity() - amount >= 0;
    }

    /** 在庫を指定分減らしたインスタンスを返却する. */
    public Inventory reduce(int amount) {
        return new Inventory(getId(), getName(), this.getQuantity() - amount);
    }
}
