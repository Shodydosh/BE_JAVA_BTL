package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;

import java.util.List;
import java.util.UUID;

public interface CartService {

    Cart getCart();

    Cart createCart();
    List<CartItem> getAllCart();
    void addItemToCart(Cart cart, Product product);

    void updateItemQuantity(Cart cart, UUID productId, int newQuantity);


    double calculateTotal(Cart cart);

    void clearCart(Cart cart);

    int getCartItemCount(Cart cart);
}
