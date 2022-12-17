package com.order.ecommerce.model;

import lombok.*;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "ecommerce_address"
)
public class Address implements Serializable {
    @Id
    @Column(
            name = "address_id",
            nullable = false,
            unique = true
    )
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID addressId;

    @Column(
            name = "address1",
            nullable = false
    )
    private String address1;

    @Column(
            name = "address2",
            nullable = false
    )
    private String address2;

    @Column(
            name = "city",
            nullable = false
    )
    private String city;

    @Column(
            name = "state",
            nullable = false
    )
    private String state;

    @Column(
            name = "zip",
            nullable = false
    )
    private String zip;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "phone",
            nullable = false
    )
    private String phone;
    @Column(
            name = "createdAt",
            nullable = false
    )
    private LocalDate createdAt;

    @PrePersist
    private void prePersistFunction(){
        if (createdAt == null) {
            createdAt = LocalDate.now();
        }
    }
}
