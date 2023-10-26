package com.javaBTL.BE_JAVA_BTL.service;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;

import java.util.List;
import java.util.UUID;

import java.util.List;

public interface CartItemService {
    CartItem addToCart(UUID cartId,Product product, int quantity);

    CartItem updateCartItem(UUID cartItemId, CartItem updatedCartItem, UUID cartId);

    List<CartItem> getAllCartItems(UUID cartId);

    void removeCartItem(UUID cartItemId, UUID cartId);


    boolean getCartItemById(UUID id);
}
