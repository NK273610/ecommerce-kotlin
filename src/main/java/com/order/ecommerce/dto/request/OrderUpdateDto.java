package com.order.ecommerce.dto.request;

import com.order.ecommerce.constants.ShippingMode;
import com.order.ecommerce.validation.EnumValidator;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;

@Getter
@Setter
public class OrderUpdateDto {

    @DecimalMin(value = "0.0", message = "Enter a valid shippingCharges")
    private double shippingCharges;

    @EnumValidator(enumClass = ShippingMode.class, message = "Invalid Shipping mode")
    private String shippingMode;
}
