package com.javaBTL.BE_JAVA_BTL.controller;

import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.service.CartItemService;
import com.javaBTL.BE_JAVA_BTL.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cartitem")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    // Thêm cartId vào các phương thức

    @PostMapping("/add/{cartId}")
    public void addToCart(@PathVariable UUID cartId, @RequestBody Product product) {
        cartItemService.addItem(cartId, product, 1);
    }

    @PutMapping("/update/{cartId}/{productId}")
    public void updateProductQuantity(@PathVariable UUID cartId, @PathVariable UUID productId, @RequestParam int newQuantity) {
        cartItemService.updateItemQuantity(cartId, productId, newQuantity);
    }

    @GetMapping("/items/{cartId}")
    public List<Product> getAllItems(@PathVariable UUID cartId) {
        return cartItemService.getAllItems(cartId);
    }

    @GetMapping("/count/{cartId}")
    public int getCount(@PathVariable UUID cartId) {
        return cartItemService.getCartItemCount(cartId);
    }

    @GetMapping("/total/{cartId}")
    public Double getTotal(@PathVariable UUID cartId) {
        return cartItemService.calculateTotal(cartId);
    }

    @DeleteMapping("/clear/{cartId}")
    public void clearCart(@PathVariable UUID cartId) {
        cartItemService.clearCart(cartId);
    }
}
