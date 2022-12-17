package com.order.ecommerce.mapper;

import com.order.ecommerce.dto.request.ProductDto;
import com.order.ecommerce.dto.response.GetProductResponse;
import com.order.ecommerce.model.Product;

public class ProductBuilder {

    public static Product buildProduct(ProductDto productDto) {
        return Product.builder()
                .description(productDto.getDescription())
                .sku(productDto.getSku())
                .price(productDto.getPrice())
                .title(productDto.getTitle())
                .build();
    }

    public static GetProductResponse buildGetProduct(Product product) {
        return GetProductResponse.builder()
                .description(product.getDescription())
                .title(product.getTitle())
                .sku(product.getSku())
                .price(product.getPrice())
                .build();
    }
}
