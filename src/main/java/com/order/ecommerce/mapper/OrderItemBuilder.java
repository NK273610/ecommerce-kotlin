package com.order.ecommerce.mapper;

import com.order.ecommerce.dto.request.OrderDto;
import com.order.ecommerce.dto.request.OrderItemDto;
import com.order.ecommerce.model.OrderItem;
import com.order.ecommerce.model.OrderItemPk;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class OrderItemBuilder {

    public static List<OrderItem> buildOrderItemList(OrderDto orderDto, UUID orderId) {
        List<OrderItem> orderItems = new ArrayList();
        for (OrderItemDto orderItem : orderDto.getOrderItems()) {
            orderItems.add(OrderItem.builder().orderItemPk
                    (new OrderItemPk(orderItem.getProductId(),
                    orderId)).quantity(orderItem.getQuantity()).build());
        }
        return orderItems;
    }
}
