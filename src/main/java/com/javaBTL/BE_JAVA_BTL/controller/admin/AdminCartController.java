package com.javaBTL.BE_JAVA_BTL.controller.admin;

import com.javaBTL.BE_JAVA_BTL.service.CartItemService;
import com.javaBTL.BE_JAVA_BTL.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/api/cart")
public class AdminCartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/all")
    public List<UUID> getAllCart() {
        return cartService.getAllCartId();
    }

}