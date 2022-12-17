package com.order.ecommerce.controller;

import com.order.ecommerce.constants.OrderStatus;
import com.order.ecommerce.dto.request.OrderUpdateDto;
import com.order.ecommerce.dto.response.GetOrderResponse;
import com.order.ecommerce.dto.response.OrderCreateUpdateResponse;
import com.order.ecommerce.dto.request.OrderDto;
import com.order.ecommerce.errorHandling.exception.OrderNotFoundException;
import com.order.ecommerce.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping({"/api/v1"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping({"/orders"})
    @Operation(
            summary = "Create an order",
            description = "Create an order"
    )
    public ResponseEntity<OrderCreateUpdateResponse> createOrder(@Valid @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.createOrder(orderDto),
                HttpStatus.CREATED);
    }

    @GetMapping({"/orders/{orderId}"})
    public ResponseEntity<GetOrderResponse> findOrderById(@PathVariable(name = "orderId") UUID orderId)
            throws OrderNotFoundException {
        return new ResponseEntity<>(orderService.findOrderById(orderId),
                HttpStatus.OK);
    }

    @PatchMapping({"/orders/{orderId}"})
    public ResponseEntity<OrderCreateUpdateResponse> updateOrderStatus(@PathVariable("orderId") UUID orderId,
                                  @RequestParam(name = "orderStatus") OrderStatus orderStatus)
            throws OrderNotFoundException {
        return new ResponseEntity<>(orderService.updateOrderStatus(orderId, orderStatus),
                HttpStatus.OK);
    }

    @PutMapping({"/orders/{orderId}"})
    public ResponseEntity<OrderDto> updateOrderDetails(@PathVariable("orderId") UUID orderId,

                                                       @Valid @RequestBody OrderUpdateDto orderUpdateDto)
    throws OrderNotFoundException {
        return new ResponseEntity<>(orderService.updateOrderDetails(orderId, orderUpdateDto),
                HttpStatus.OK);
    }
}
