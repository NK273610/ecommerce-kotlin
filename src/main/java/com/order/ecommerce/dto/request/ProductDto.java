package com.order.ecommerce.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class ProductDto {

    @NotEmpty(message = "sku cannot be null or empty")
    private String sku;

    @NotEmpty(message = "title cannot be null or empty")
    private String title;

    @NotEmpty(message = "description cannot be null or empty")
    private String description;

    @DecimalMin(value = "1.0", message = "Enter a valid price")
    private double price;
}
