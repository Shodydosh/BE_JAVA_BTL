package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.model.Cart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class CartServiceImpl implements CartService {
    private Cart cart = new Cart();

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void addToCart(Product product) {
        cart.addItem(product);
    }

    @Override
    public void updateProductQuantity(UUID productId, int newQuantity) {
        cart.updateProductQuantity(productId, newQuantity);
    }

    @Override
    public void clearCart() {
        cart.clearCart();
    }

    @Override
    public List<CartItem> getAllItems() {
        return cart.getItems();
    }

    @Override
    public int getCount() {
        return cart.getCount();
    }

    @Override
    public Double getTotal() {
        return cart.getTotal();
    }
}

