package com.order.ecommerce.service.order;

import com.order.ecommerce.constants.OrderStatus;
import com.order.ecommerce.constants.PaymentMode;
import com.order.ecommerce.constants.ShippingMode;
import com.order.ecommerce.dto.request.OrderUpdateDto;
import com.order.ecommerce.dto.response.GetOrderResponse;
import com.order.ecommerce.dto.response.OrderCreateUpdateResponse;
import com.order.ecommerce.dto.request.OrderDto;
import com.order.ecommerce.errorHandling.exception.OrderNotFoundException;
import com.order.ecommerce.mapper.OrderBuilder;
import com.order.ecommerce.mapper.OrderItemBuilder;
import com.order.ecommerce.repository.OrderItemRepository;
import com.order.ecommerce.repository.OrderRepository;
import javax.transaction.Transactional;

import com.order.ecommerce.service.payment.PaymentService;
import com.order.ecommerce.service.address.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    PaymentService paymentService;

    public OrderCreateUpdateResponse updateOrderStatus(UUID orderId, OrderStatus orderStatus)
            throws OrderNotFoundException {
        var order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException());
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return new OrderCreateUpdateResponse(order.getOrderId().toString(),
                order.getOrderStatus().name());
    }

    public GetOrderResponse findOrderById(UUID orderId) throws OrderNotFoundException {
        var order =  orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException());
        return OrderBuilder.buildGetOrder(order);
    }

    @Transactional
    public OrderCreateUpdateResponse createOrder(OrderDto orderDto) {

        var billing = addressService.createAddress(orderDto.getBillingAddress());
        var shipping = addressService.createAddress(orderDto.getShippingAddress());
        var payment = paymentService.createPayment(orderDto.getTotalAmt(),
                PaymentMode.valueOf(orderDto.getPaymentMode()));

       var order =  orderRepository.save(OrderBuilder
               .buildOrder(orderDto, billing, shipping, payment));
        orderItemRepository.saveAll(OrderItemBuilder
                .buildOrderItemList(orderDto, order.getOrderId()));

       return new OrderCreateUpdateResponse(order.getOrderId().toString(),
               order.getOrderStatus().name());
     }

     public OrderDto updateOrderDetails(UUID orderId, OrderUpdateDto orderUpdateDto)
     throws OrderNotFoundException{
         var order =  orderRepository.findById(orderId).orElseThrow(
                 () -> new OrderNotFoundException());
         order.setShippingCharges(orderUpdateDto.getShippingCharges());
         order.setShippingMode(ShippingMode.valueOf(orderUpdateDto
                 .getShippingMode()));
         orderRepository.save(order);
         return OrderBuilder.buildGetOrder(order);
     }
}
