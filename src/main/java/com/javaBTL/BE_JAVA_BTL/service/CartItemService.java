package com.javaBTL.BE_JAVA_BTL.service;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;

import java.util.List;
import java.util.UUID;

public interface CartItemService {
    void addItem(UUID cartId, Product product, int quantity);

    CartItem updateItemQuantity(UUID cartId, UUID productId, int newQuantity);

    List<Product> getAllItems(UUID cartId);

    Product getItembyID(UUID cartId, UUID productId);

    void clearCart(UUID cartId);

    double calculateTotal(UUID cartId);

    int getCartItemCount(UUID cartId);

}
