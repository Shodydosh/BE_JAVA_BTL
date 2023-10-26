package com.javaBTL.BE_JAVA_BTL.controller;

import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.service.CartItemService;
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
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/all")
    public List<UUID> getAllCart() {
        return cartService.getAllCartId();
    }
    @GetMapping("/{cartId}")
    public List<CartItem> findById(@PathVariable UUID cartId) {
        return cartItemService.findByCartId(cartId);
    }
}