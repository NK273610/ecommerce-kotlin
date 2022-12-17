package com.order.ecommerce.model;

import com.order.ecommerce.constants.PaymentMode;
import lombok.*;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "ecommerce_payment"
)
public class Payment implements Serializable {
    @Id
    @Column(
            name = "payment_id",
            nullable = false,
            unique = true
    )
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID paymentId;
    @Column(
            name = "amount",
            nullable = false
    )
    private double amount;
    @Column(
            name = "payment_mode",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    @Column(
            name = "confirmation_number",
            nullable = false
    )
    private String confirmationNumber;
    @Column(
            name = "payment_status",
            nullable = false
    )
    private String paymentStatus;
    @Column(
            name = "createdAt",
            nullable = false
    )
    private LocalDate createdAt;

    @PrePersist
    private void prePersistFunction() {
        if (createdAt == null) {
            createdAt = LocalDate.now();
        }

        if (confirmationNumber == null) {
            confirmationNumber = UUID.randomUUID().toString();
        }
    }
}
