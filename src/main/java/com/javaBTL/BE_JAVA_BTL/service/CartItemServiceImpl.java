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
    public List<Product> findByCartId(UUID cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

}
