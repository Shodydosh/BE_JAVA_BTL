package com.javaBTL.BE_JAVA_BTL.controller;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cartitems")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/add")
    public ResponseEntity<CartItem> addCartItem(@RequestBody Product product,@RequestBody int quantity) {
        CartItem cartItem = cartItemService.addToCart(product,quantity);
        return ResponseEntity.ok(cartItem);
    }

    // Sửa số lượng sản phẩm trong giỏ hàng
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable UUID cartItemId, @RequestBody CartItem cartItem) {
        CartItem updatedCartItem = cartItemService.updateCartItem(cartItemId, cartItem);
        return ResponseEntity.ok(updatedCartItem);
    }

    // Xem tất cả sản phẩm trong giỏ hàng
    @GetMapping("/all")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    // Tính tổng số tiền sản phẩm trong giỏ hàng
    @GetMapping("/total")
    public ResponseEntity<Double> calculateTotalPrice() {
        double totalPrice = cartItemService.calculateTotalPrice();
        return ResponseEntity.ok(totalPrice);
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeCartItem(@PathVariable UUID cartItemId) {
        cartItemService.removeCartItem(cartItemId);
        return ResponseEntity.ok("CartItem removed from cart.");
    }
}
