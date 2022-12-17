package com.order.ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemDto {

    @NotNull(message = "product id cannot be null")
    private UUID productId;

    @NotEmpty(message = "quantity cannot be null or empty")
    private String quantity;
}
