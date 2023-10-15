package com.javaBTL.BE_JAVA_BTL.service;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.repository.CartItemRepository;
import com.javaBTL.BE_JAVA_BTL.repository.CartRepository;
import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Cart getCart() {
        return cartRepository.findAll().get(0);
    }

    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    @Override
    public List<CartItem> getAllCart() {
        return cartItemRepository.findAll();
    }

    @Override
    public void addItemToCart(Cart cart, Product product) {
        cart.addItem(product);
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Cart cart, UUID productId, int newQuantity) {
        cart.updateProductQuantity(productId, newQuantity);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Cart cart, UUID productId) {
        cart.removeItem(productId);
        cartRepository.save(cart);
    }

    @Override
    public double calculateTotal(Cart cart) {
        return cart.getTotal();
    }

    @Override
    public void clearCart(Cart cart) {
        cart.clearCart();
        cartRepository.save(cart);
    }

    @Override
    public int getCartItemCount(Cart cart) {
        return cart.getCount();
    }
}
