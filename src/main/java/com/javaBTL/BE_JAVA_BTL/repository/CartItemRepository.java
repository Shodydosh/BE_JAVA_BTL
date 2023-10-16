package com.javaBTL.BE_JAVA_BTL.repository;

import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    @Query("SELECT ci.product FROM CartItem ci WHERE ci.cart.id = :cartId")
    List<Product> getAllItems(@Param("cartId") UUID cartId);
    @Query("DELETE FROM CartItem ci WHERE ci.cart.id = :cartId")
    void deleteAll(@Param("cartId") UUID cartId);

}
