package com.javaBTL.BE_JAVA_BTL.repository;
import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CartRepository extends JpaRepository<CartItem, UUID> {
    Cart getCart();
    void addToCart(Product product);
    void updateProductQuantity(UUID productId, int newQuantity);
    void clearCart();
    List<CartItem> getAllItems();
    int getCount();
    Double getTotal();
}
