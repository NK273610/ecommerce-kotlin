package com.order.ecommerce.integration;

import com.jayway.jsonpath.JsonPath;
import com.order.ecommerce.AbstractIntegrationTest;
import com.order.ecommerce.constants.OrderStatus;
import com.order.ecommerce.constants.PaymentMode;
import com.order.ecommerce.constants.ShippingMode;
import com.order.ecommerce.dto.request.AddressDto;
import com.order.ecommerce.dto.request.OrderDto;
import com.order.ecommerce.dto.request.OrderItemDto;
import com.order.ecommerce.dto.request.OrderUpdateDto;
import com.order.ecommerce.integration.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createOrderTest() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/v1/orders")
                .content(JsonUtil.asJsonString(this.createOrderDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").exists());
    }

    @Test
    void updateOrderTest() throws Exception {
        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
                .post("/api/v1/orders")
                .content(JsonUtil.asJsonString(this.createOrderDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String orderId = JsonPath.read(result.getResponse().getContentAsString(), "$.orderId");

        mockMvc.perform( MockMvcRequestBuilders
                .patch("/api/v1/orders/"+orderId)
                .param("orderStatus", OrderStatus.COMPLETED.name())
                .content(JsonUtil.asJsonString(this.createOrderDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus")
                        .value(OrderStatus.COMPLETED.name()));
    }

    @Test
    void updateOrder() throws Exception {
        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
                .post("/api/v1/orders")
                .content(JsonUtil.asJsonString(this.createOrderDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String orderId = JsonPath.read(result.getResponse().getContentAsString(), "$.orderId");

        mockMvc.perform( MockMvcRequestBuilders
                .patch("/api/v1/orders/"+orderId)
                .content(JsonUtil.asJsonString(this.buildOrderUpdateDto(2.0,
                        ShippingMode.PICKUP)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.shippingMode")
                        .value(ShippingMode.PICKUP.name()));
    }



    @Test
    void testGetOrderById() throws Exception {
        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
                .post("/api/v1/orders")
                .content(JsonUtil.asJsonString(this.createOrderDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String orderId = JsonPath.read(result.getResponse().getContentAsString(), "$.orderId");

        mockMvc.perform(get("/api/v1/orders/" + orderId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private OrderUpdateDto buildOrderUpdateDto(double shippingCharges, ShippingMode shippingMode) {
        OrderUpdateDto orderUpdateDto = new OrderUpdateDto();
        orderUpdateDto.setShippingCharges(shippingCharges);
        orderUpdateDto.setShippingMode(shippingMode.name());
        return orderUpdateDto;
    }

    private OrderDto createOrderDto() {
        var product = productRepository.findAll().get(0);
        var addressDto = new AddressDto();
        addressDto.setAddress1(RandomStringUtils.randomAlphabetic(6));
        addressDto.setAddress2(RandomStringUtils.randomAlphabetic(6));
        addressDto.setCity(RandomStringUtils.randomAlphabetic(6));
        addressDto.setEmail(RandomStringUtils.randomAlphabetic(6) + "@gmail");
        addressDto.setState(RandomStringUtils.randomAlphabetic(6));
        addressDto.setPhone(RandomStringUtils.randomNumeric(6));
        addressDto.setZip(RandomStringUtils.randomAlphabetic(6));
        var orderDto = new OrderDto();
        orderDto.setAmount(5);
        orderDto.setPaymentMode(PaymentMode.CASH.name());
        orderDto.setTax(5);
        orderDto.setCustomerId(RandomStringUtils.randomAlphabetic(6));
        orderDto.setSubTotal(5);
        orderDto.setTitle(RandomStringUtils.randomAlphabetic(6));
        orderDto.setTotalAmt(5);
        orderDto.setShippingCharges(5);
        orderDto.setShippingMode(ShippingMode.CURBSIDE_PICKUP.name());
        orderDto.setBillingAddress(addressDto);
        orderDto.setShippingAddress(addressDto);
        orderDto.setOrderItems(Arrays.asList(new OrderItemDto(product.getProductId(),
                "25")));
        return orderDto;
    }
}
