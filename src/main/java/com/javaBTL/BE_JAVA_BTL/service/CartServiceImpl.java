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
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @CrossOrigin
    @Override
    public List<UUID> getAllCartId() {
        return cartRepository.getAllCartId();
    }
    @CrossOrigin
    @Override
    public Cart findById(UUID cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

}
