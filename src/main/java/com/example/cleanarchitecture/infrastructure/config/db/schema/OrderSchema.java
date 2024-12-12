package com.example.cleanarchitecture.infrastructure.config.db.schema;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.cleanarchitecture.domain.order.model.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "orders")
@NoArgsConstructor
public class OrderSchema extends AbstractEntitySchema<Long> {

    public OrderSchema(Order order) {
        this.setId(order.getId());
        this.setQuantity(order.getQuantity());
        this.setCustomerInfo(order.getCustomerInfo());
    }

    @Column(nullable = false)
    private String customerInfo;

    @Column(nullable = false)
    private int quantity;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne()
    @JoinColumn(name = "inventory_id")
    private InventorySchema inventorySchema;

}
