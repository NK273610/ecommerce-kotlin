package com.order.ecommerce.model;

import lombok.*;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(
        name = "ecommerce_order_item"
)
public class OrderItem implements Serializable {
    @EmbeddedId
    private OrderItemPk orderItemPk;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            insertable = false,
            updatable = false
    )
    private Product product;

    @ManyToOne
    @JoinColumn(
            name = "order_id",
            insertable = false,
            updatable = false
    )
    private Order order;

    @Column(
            name = "quantity",
            nullable = false
    )
    private String quantity;
}
