package com.order.ecommerce.service.product;

import com.order.ecommerce.dto.request.ProductDto;
import com.order.ecommerce.dto.response.GetProductResponse;
import com.order.ecommerce.dto.response.ProductCreateResponse;
import com.order.ecommerce.errorHandling.exception.ProductNotFoundException;
import com.order.ecommerce.mapper.ProductBuilder;
import com.order.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductCreateResponse createProduct(ProductDto productDto) {
        var product = productRepository.save(ProductBuilder.buildProduct(productDto));
        return new ProductCreateResponse(product.getProductId().toString());
    }

    public GetProductResponse getProduct(UUID productId)
            throws ProductNotFoundException {
         var product =  productRepository.findById(productId)
                 .orElseThrow(() -> new ProductNotFoundException());;
         return ProductBuilder.buildGetProduct(product);
    }
}
