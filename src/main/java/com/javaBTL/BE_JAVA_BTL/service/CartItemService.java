package com.javaBTL.BE_JAVA_BTL.service;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;

import java.util.List;
import java.util.UUID;

import java.util.List;

public interface CartItemService {
    CartItem addToCart(UUID cartId,Product product, int quantity);
    List<Product> findByCartId(UUID cartId);
}
