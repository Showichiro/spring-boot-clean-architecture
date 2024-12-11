package com.example.cleanarchitecture.infrastructure.config.db.schema;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.cleanarchitecture.core.product.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSchema extends AbstractEntitySchema<Long> {
    @Column
    private String name;
    @Column
    private Long price;

    @Column(nullable = false)
    private boolean enabled;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    private ProductSchema(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setEnabled(product.isEnabled());
    }

    public static ProductSchema fromProduct(Product product) {
        return new ProductSchema(product);
    }

    public Product toProduct() {
        var product = new Product(this.getName(), this.getPrice(),
                this.isEnabled());
        product.setId(this.getId());
        return product;
    }
}
