package com.order.ecommerce.dto.request;

import com.order.ecommerce.constants.PaymentMode;
import com.order.ecommerce.constants.ShippingMode;
import com.order.ecommerce.validation.EnumValidator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Getter
@Setter
public class OrderDto {

    @NotEmpty(message = "customerId cannot be null or empty")
    private String customerId;

    @DecimalMin(value = "0.1", message = "Enter a valid subTotal")
    private double subTotal;

    @DecimalMin(value = "0.1", message = "Enter a valid totalAmt")
    private double totalAmt;

    @DecimalMin(value = "0.1", message = "Enter a valid tax")
    private double tax;

    @DecimalMin(value = "0.0", message = "Enter a valid shippingCharges")
    private double shippingCharges;

    @NotEmpty(message = "title cannot be null or empty")
    private String title;

    @EnumValidator(enumClass = ShippingMode.class, message = "Invalid Shipping mode")
    private String shippingMode;

    @DecimalMin(value = "0.1", message = "Enter a valid amount")
    private double amount;

    @EnumValidator(enumClass = PaymentMode.class, message = "Invalid Payment mode")
    private String paymentMode;

    @Valid
    private AddressDto billingAddress;

    @Valid
    private AddressDto shippingAddress;

    @Valid
    @NotEmpty(message = "orderItems cannot be empty")
    private List<OrderItemDto> orderItems;
}
