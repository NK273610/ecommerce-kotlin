package com.order.ecommerce.model;

import com.order.ecommerce.constants.OrderStatus;
import com.order.ecommerce.constants.ShippingMode;
import lombok.*;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        name = "ecommerce_order"
)
public class Order implements Serializable {
    @Id
    @Column(
            name = "order_id",
            nullable = false,
            unique = true
    )
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;

    @Column(
            name = "order_status"
    )
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(
            name = "customer_id"
    )
    private String customerId;

    @Column(
            name = "sub_total"
    )
    private double subTotal;
    @Column(
            name = "total_amt"
    )
    private double totalAmt;
    @Column(
            name = "tax"
    )
    private double tax;
    @Column(
            name = "shipping_charges"
    )
    private double shippingCharges;
    @Column(
            name = "title"
    )
    private String title;
    @Column(
            name = "shipping_mode"
    )
    @Enumerated(EnumType.STRING)
    private ShippingMode shippingMode;
    @Column(
            name = "created_at"
    )
    private LocalDateTime createdAt;
    @OneToOne(
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(
            referencedColumnName = "payment_id",
            name = "payment_id"
    )
    private Payment payment;

    @OneToOne
    @JoinColumn(
            referencedColumnName = "address_id",
            name = "billing_address_id"
    )
    private Address billingAddress;

    @OneToOne
    @JoinColumn(
            referencedColumnName = "address_id",
            name = "shipping_address_id"
    )
    private Address shippingAddress;

    @OneToMany(
            targetEntity = OrderItem.class,
            fetch = FetchType.LAZY,
            mappedBy = "order"
    )
    private List<OrderItem> orderItems;

    @PrePersist
    private void prePersistFunction(){
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
