package com.order.ecommerce.integration;

import com.order.ecommerce.AbstractIntegrationTest;
import com.order.ecommerce.dto.request.ProductDto;
import com.order.ecommerce.integration.util.JsonUtil;
import com.order.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ProductTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetProductById() throws Exception {
        var product = productRepository.findAll().get(0);
        mockMvc.perform(get("/api/v1/products/" + product.getProductId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createProduct() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/v1/products")
                .content(JsonUtil.asJsonString(this.createProductDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").exists());
    }

    private ProductDto createProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(RandomStringUtils.randomAlphabetic(6));
        productDto.setPrice(10);
        productDto.setSku(RandomStringUtils.randomAlphabetic(6));
        productDto.setTitle(RandomStringUtils.randomAlphabetic(6));
        return productDto;
    }
}
