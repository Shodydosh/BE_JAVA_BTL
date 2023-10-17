package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.repository.CartItemRepository;
import com.javaBTL.BE_JAVA_BTL.repository.CartRepository;
import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.CartItemInfo;
import com.javaBTL.BE_JAVA_BTL.model.Product;
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
    public void addItem(UUID cartId, Product product, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cartRepository.findById(cartId).orElse(null)); // Lấy giỏ hàng dựa trên cartId
        cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateItemQuantity(UUID cartId, UUID productId, int newQuantity) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(productId);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(newQuantity);
            return cartItemRepository.save(cartItem);
        }
        return null;
    }

    @Override
    public List<CartItemInfo> getAllItems(UUID cartId) {
        return cartItemRepository.getAllItems(cartId);
    }

    @Override
    public Product getItembyID(UUID cartId, UUID productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public void clearCart(UUID cartId) {
        cartItemRepository.deleteAll(cartId);
    }

    @Override
    public double calculateTotal(UUID cartId) {
        // Thêm logic tính tổng giá trị sản phẩm trong giỏ hàng dựa trên cartId
        return 0.0; // Cần triển khai logic tính tổng thực tế ở đây
    }

    @Override
    public int getCartItemCount(UUID cartId) {
        return (int) cartItemRepository.count();
    }

}
