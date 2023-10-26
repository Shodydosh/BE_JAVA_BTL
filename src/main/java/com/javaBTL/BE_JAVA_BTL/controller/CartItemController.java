package com.javaBTL.BE_JAVA_BTL.controller;
import com.javaBTL.BE_JAVA_BTL.model.Cart;
import com.javaBTL.BE_JAVA_BTL.model.CartItem;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.service.CartItemService;
import com.javaBTL.BE_JAVA_BTL.service.CartService;
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
    private CartService cartService;
    @Autowired
    public CartItemController(CartItemService cartItemService, CartService cartService) {
        this.cartItemService = cartItemService;
        this.cartService = cartService; // Initialize cartService
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/add/{cartId}")
    public ResponseEntity<CartItem> addCartItem(@PathVariable UUID cartId, @RequestBody CartItem cartItemRequest) {
        Product product = cartItemRequest.getProduct();
        int quantity = cartItemRequest.getQuantity();
        // Create a new CartItem and set its cartId
        CartItem cartItem = new CartItem();
        // Assuming that cartService is an instance of CartService
        Cart cart = cartService.findById(cartId);
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        // Save or add the cartItem to the cart
        cartItem = cartItemService.addToCart(cartId, product, quantity);
        return ResponseEntity.ok(cartItem);
    }



    // Sửa số lượng sản phẩm trong giỏ hàng
    @PutMapping("/update/{cartId}") // cần truyền vào cartid để biết đang sửa sản phẩm nào và ở giỏ hàng nào
    public ResponseEntity<CartItem> updateCartItem(@PathVariable UUID cartItemId, @RequestBody CartItem cartItem, UUID cartId) {
        CartItem updatedCartItem = cartItemService.updateCartItem(cartItemId, cartItem, cartId);
        return ResponseEntity.ok(updatedCartItem);
    }

    // Xem tất cả sản phẩm trong giỏ hàng
    @GetMapping("/all/{cartId}")  // cần truyền vào cartid để biết đang xem giỏ hàng nào
    public ResponseEntity<List<CartItem>> getAllCartItems(UUID cartId) {
        List<CartItem> cartItems = cartItemService.getAllCartItems(cartId);
        return ResponseEntity.ok(cartItems);
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/remove/{cartItemId}/{cartId}")
    public ResponseEntity<String> removeCartItem(@PathVariable UUID cartItemId, @PathVariable UUID cartId) {
        cartItemService.removeCartItem(cartItemId, cartId);
        return ResponseEntity.ok("CartItem removed from cart.");
    }
}
