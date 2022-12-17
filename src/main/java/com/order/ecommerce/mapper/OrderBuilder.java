package com.order.ecommerce.mapper;

import com.order.ecommerce.constants.OrderStatus;
import com.order.ecommerce.constants.PaymentMode;
import com.order.ecommerce.constants.ShippingMode;
import com.order.ecommerce.dto.request.AddressDto;
import com.order.ecommerce.dto.request.OrderDto;
import com.order.ecommerce.dto.request.OrderItemDto;
import com.order.ecommerce.dto.response.GetOrderResponse;
import com.order.ecommerce.model.Address;
import com.order.ecommerce.model.Order;
import com.order.ecommerce.model.Payment;

import java.util.Arrays;

public class OrderBuilder {

    public static Order buildOrder(OrderDto orderDto,
                                   Address billing,
                                   Address shipping,
                                   Payment payment) {
        if (orderDto == null) {
            return null;
        }

        return Order.builder()
                .billingAddress(billing)
                .shippingAddress(shipping)
                .payment(payment)
                .orderStatus(OrderStatus.RECEIVED)
                .tax(orderDto.getTax())
                .totalAmt(orderDto.getTotalAmt())
                .subTotal(orderDto.getSubTotal())
                .shippingMode(ShippingMode.valueOf(orderDto.getShippingMode()))
                .shippingCharges(orderDto.getShippingCharges())
                .customerId(orderDto.getCustomerId())
                .title(orderDto.getTitle())
                .build();
    }

    public static GetOrderResponse buildGetOrder(Order order) {
        if (order == null) {
            return null;
        }

        var orderDto = new GetOrderResponse();
        orderDto.setAmount(order.getPayment().getAmount());
        orderDto.setPaymentMode(order.getPayment().getPaymentMode().name());
        orderDto.setTax(order.getTax());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setSubTotal(order.getSubTotal());
        orderDto.setTitle(order.getTitle());
        orderDto.setTotalAmt(order.getTotalAmt());
        orderDto.setShippingCharges(order.getShippingCharges());
        orderDto.setShippingMode(order.getShippingMode().name());
        return orderDto;
    }
}
