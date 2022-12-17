package com.order.ecommerce.model;

import lombok.*;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "ecommerce_product"
)
public class Product implements Serializable {
    @Id
    @Column(
            name = "product_id",
            nullable = false,
            unique = true
    )
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productId;

    @Column(
            name = "sku",
            nullable = false
    )
    private String sku;
    @Column(
            name = "title",
            nullable = false
    )
    private String title;
    @Column(
            name = "description",
            nullable = false
    )
    private String description;
    @Column(
            name = "price",
            nullable = false
    )
    private double price;
    @Column(
            name = "createdAt",
            nullable = false
    )
    private LocalDate createdAt;

    @OneToMany(
            targetEntity = OrderItem.class,
            fetch = FetchType.LAZY,
            mappedBy = "product"
    )
    private List<OrderItem> orderItems;

    @PrePersist
    private void prePersistFunction(){
        if (createdAt == null) {
            createdAt = LocalDate.now();
        }
    }
}
