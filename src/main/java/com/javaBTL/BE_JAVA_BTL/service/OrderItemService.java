package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.OrderItem;
import java.util.List;
import java.util.UUID;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItem orderItem);
    OrderItem getOrderItemById(UUID id);
    List<OrderItem> getOrderItemsByOrderId(UUID orderId);
    OrderItem updateOrderItem(UUID id, OrderItem orderItem);
    void deleteOrderItem(UUID id);
}
