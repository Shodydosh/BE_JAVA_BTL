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
    public Cart getCart() {
        List<Cart> carts = cartRepository.findAll();
        if (!carts.isEmpty()) {
            return carts.get(0);
        } else {
            Cart cart = createCart();
            cartRepository.save(cart);
            return cart;
        }
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
    @Transactional
    public void addItemToCart(Cart cart, Product product) {
        if (cart == null) {
            cart = createCart(); // Create a new cart if it doesn't exist
        }

        // Check if the product is already in the cart
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            // Update the quantity of the existing cart item
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            // Add a new cart item for the product
            CartItem cartItem = new CartItem(product, 1);
            cartItem.setCart(cart); // Set the cart for the cart item
            cart.getItems().add(cartItem);
        }

        cartRepository.save(cart); // Save the updated cart
    }

    @Override
    public void updateItemQuantity(Cart cart, UUID productId, int newQuantity) {

    }


    @Override
    public double calculateTotal(Cart cart) {
        BigDecimal sum = BigDecimal.ZERO; // Initialize the sum as BigDecimal.ZERO
        for (CartItem item : cart.getItems()) {

                BigDecimal price = new BigDecimal(item.getProduct().getPrice());
                BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
                sum = sum.add(price.multiply(quantity));
        }
        return sum.doubleValue(); // Convert the BigDecimal sum to a double for the return value
    }
    @Override
    public void clearCart(Cart cart) {
        cartRepository.deleteAll();
        cartRepository.save(cart);
    }

    @Override
    public int getCartItemCount(Cart cart) {
        return cart.getItems().size();
    }
}
