package com.order.ecommerce.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetProductResponse {
    private String sku;
    private String title;
    private String description;
    private double price;
}
