package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.Order;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(UUID id);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(UUID userId);
    Order updateOrderStatus(UUID id, String status);
    void deleteOrder(UUID id);
}