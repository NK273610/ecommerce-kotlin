package com.order.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderCreateUpdateResponse {

    private String orderId;
    private String orderStatus;
}
