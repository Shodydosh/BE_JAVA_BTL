package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;

import java.util.List;
import java.util.UUID;

public interface CartService {

    CartItem createCartItem(Product product, int quantity);

    CartItem updateItemQuantity(Cart cart, UUID productId, int newQuantity);

    List<CartItem> getAllItems(Cart cart);

    List<Cart> getAllCart();

    void clearCart(Cart cart);

    void addItemToCart(Cart cart, Product product, int quantity);

    double calculateTotal(Cart cart);

    int getCartItemCount(Cart cart);

    Cart getCart();
}
