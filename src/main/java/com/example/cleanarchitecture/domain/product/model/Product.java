package com.example.cleanarchitecture.domain.product.model;

import com.example.cleanarchitecture.core.domain.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbstractEntity<Long> {
    private String name;
    private Long price;
    private boolean enabled;
}
