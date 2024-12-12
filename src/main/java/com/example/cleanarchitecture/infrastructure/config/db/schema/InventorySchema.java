package com.example.cleanarchitecture.infrastructure.config.db.schema;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.cleanarchitecture.domain.inventory.model.Inventory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "inventories")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InventorySchema extends AbstractEntitySchema<Long> {

    public InventorySchema(Inventory inventory) {
        this.setId(inventory.getId());
        this.setName(inventory.getName());
        this.setQuantity(inventory.getQuantity());
    }

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int quantity;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
