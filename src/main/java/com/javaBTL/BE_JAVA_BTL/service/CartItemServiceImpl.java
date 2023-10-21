package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.repository.CartItemRepository;
import com.javaBTL.BE_JAVA_BTL.repository.CartRepository;
import com.javaBTL.BE_JAVA_BTL.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CartItem addToCart(UUID cartId,Product product, int quantity) {
        CartItem cartItem = new CartItem();
        Optional<Cart> cart = cartRepository.findById(cartId);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart.get());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(UUID cartItemId, CartItem updatedCartItem) {

        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isPresent()) {
            CartItem item = cartItem.get();
            item.setQuantity(updatedCartItem.getQuantity());
            return cartItemRepository.save(item);
        }
        return null;
    }
    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }
    @Override
    public double calculateTotalPrice() {
        double totalPrice = 0;
        List<CartItem> cartItems = cartItemRepository.findAll();
        for (CartItem item : cartItems) {
            String priceStr = item.getProduct().getPrice();
            priceStr = priceStr.replaceAll("[^0-9]", ""); // Loại bỏ tất cả ký tự không phải số
            int price = Integer.parseInt(priceStr);
            totalPrice += price * item.getQuantity();
        }
        return totalPrice;
    }
    @Override
    public void removeCartItem(UUID cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
    @Override
    public boolean getCartItemById(UUID id) {
        return cartItemRepository.findById(id).isPresent();
    }
}
