package com.javaBTL.BE_JAVA_BTL.service;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.repository.CartItemRepository;
import com.javaBTL.BE_JAVA_BTL.repository.CartRepository;
import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Override
    public void addItemToCart(Cart cart, Product product, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);
    }

    @Override
    public double calculateTotal(Cart cart) {
        double total = 0;
        for (CartItem cartItem : cart.getItems()) {

            total += Double.parseDouble(cartItem.getProduct().getPrice()) * cartItem.getQuantity();
        }
        return total;

    }

    @Override
    public CartItem createCartItem(Product product, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateItemQuantity(Cart cart, UUID productId, int newQuantity) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(productId);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(newQuantity);
            return cartItemRepository.save(cartItem);
        }
        return null;
    }

    @Override
    public List<CartItem> getAllItems(Cart cart) {
        return cartItemRepository.getAllItems(cart.getId());
    }

    @Override
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public void clearCart(Cart cart) {
        cartItemRepository.deleteAll();
    }
    @Override
    public int getCartItemCount(Cart cart) {
        return (int) cartItemRepository.count();
    }
    @Override
    public Cart getCart() {
        return cartRepository.findAll().get(0);
    }
}
