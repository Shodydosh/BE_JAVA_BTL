package com.javaBTL.BE_JAVA_BTL.controller;

import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public Cart getCart() {
        return cartService.getCart();
    }

    @PostMapping("/add")
    public void addToCart(@RequestBody Product product) {
        cartService.addToCart(product);
    }



    @PutMapping("/update/{productId}")
    public void updateProductQuantity(@PathVariable UUID productId, @RequestParam int newQuantity) {
        cartService.updateProductQuantity(productId, newQuantity);
    }

    @DeleteMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }

    @GetMapping("/items")
    public List<CartItem> getAllItems() {
        return cartService.getAllItems();
    }

    @GetMapping("/count")
    public int getCount() {
        return cartService.getCount();
    }

    @GetMapping("/total")
    public Double getTotal() {
        return cartService.getTotal();
    }
}
