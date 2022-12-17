package com.order.ecommerce.model;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public final class OrderItemPk implements Serializable {
    @Column(
            name = "product_id",
            nullable = false
    )
    private UUID productId;

    @Column(
            name = "order_id",
            nullable = false
    )
    private UUID orderId;
}
