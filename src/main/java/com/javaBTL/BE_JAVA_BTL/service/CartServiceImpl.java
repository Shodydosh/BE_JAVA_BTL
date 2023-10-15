package com.javaBTL.BE_JAVA_BTL.service;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.repository.CartItemRepository;
import com.javaBTL.BE_JAVA_BTL.repository.CartRepository;
import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public void addItemToCart(Cart cart, Product product) {
        if (cart == null) {
            cart = createCart(); // Create a new cart if it doesn't exist
        }
        cart.addItem(product); // This method should correctly add the product to the cart
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Cart cart, UUID productId, int newQuantity) {
        cart.updateProductQuantity(productId, newQuantity);
        cartRepository.save(cart);
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
