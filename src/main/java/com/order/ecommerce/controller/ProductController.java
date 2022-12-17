package com.order.ecommerce.controller;

import com.order.ecommerce.dto.request.ProductDto;
import com.order.ecommerce.dto.response.GetProductResponse;
import com.order.ecommerce.dto.response.ProductCreateResponse;
import com.order.ecommerce.errorHandling.exception.ProductNotFoundException;
import com.order.ecommerce.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping({"/api/v1"})
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping({"/products"})
    @Operation(
            summary = "Create a product",
            description = "Create a product"
    )
    public ResponseEntity<ProductCreateResponse> createProduct(@Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto),
                HttpStatus.CREATED);
    }

    @GetMapping({"/products/{productId}"})
    @Operation(
            summary = "Get a product",
            description = "Get a product"
    )
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable(name = "productId")
                                                     UUID productId)
            throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getProduct(productId),
                HttpStatus.OK);
    }
}
