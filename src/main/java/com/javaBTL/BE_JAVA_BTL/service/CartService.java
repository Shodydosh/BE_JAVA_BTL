package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;

import java.util.List;
import java.util.UUID;

public interface CartService {

    Cart getCart();

    void addToCart(Product product);

    void updateProductQuantity(UUID productId, int newQuantity);

    void clearCart();

    List<CartItem> getAllItems();

    int getCount();

    Double getTotal();
}
