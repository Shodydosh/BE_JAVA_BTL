package com.javaBTL.BE_JAVA_BTL.service;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;

import java.util.List;
import java.util.UUID;

import java.util.List;

public interface CartItemService {
    CartItem addToCart(Product product, int quantity);

    CartItem updateCartItem(UUID cartItemId, CartItem updatedCartItem);

    List<CartItem> getAllCartItems();

    double calculateTotalPrice();

    void removeCartItem(UUID cartItemId);


    boolean getCartItemById(UUID id);
}
