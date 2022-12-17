package com.order.ecommerce.repository;

import com.order.ecommerce.model.OrderItem;
import com.order.ecommerce.model.OrderItemPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {
}
