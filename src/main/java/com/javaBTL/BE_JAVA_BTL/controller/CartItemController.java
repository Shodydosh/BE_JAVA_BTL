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
    @PostMapping("/add")   // cần truyền vào cartid để biết mình đang thêm vào giỏ hàng nào
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItemRequest) {
        Product product = cartItemRequest.getProduct();
        int quantity = cartItemRequest.getQuantity();

        CartItem cartItem = cartItemService.addToCart(product, quantity);

        return ResponseEntity.ok(cartItem);
    }

    // Sửa số lượng sản phẩm trong giỏ hàng
    @PutMapping("/update/{cartItemId}") // cần truyền vào cartid để biết đang sửa sản phẩm nào và ở giỏ hàng nào
    public ResponseEntity<CartItem> updateCartItem(@PathVariable UUID cartItemId, @RequestBody CartItem cartItem) {
        CartItem updatedCartItem = cartItemService.updateCartItem(cartItemId, cartItem);
        return ResponseEntity.ok(updatedCartItem);
    }

    // Xem tất cả sản phẩm trong giỏ hàng
    @GetMapping("/all")  // cần truyền vào cartid để biết đang xem giỏ hàng nào
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    // Tính tổng số tiền sản phẩm trong giỏ hàng
    @GetMapping("/total") // cần truyền vào cartid để biết đang xem giỏ hàng nào
    public ResponseEntity<Double> calculateTotalPrice() {
        double totalPrice = cartItemService.calculateTotalPrice();
        return ResponseEntity.ok(totalPrice);
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/remove/{cartItemId}")    // truyền thêm cart id giúp biết đang xóa giỏ hàng nào
    public ResponseEntity<String> removeCartItem(@PathVariable UUID cartItemId) {
        cartItemService.removeCartItem(cartItemId);
        return ResponseEntity.ok("CartItem removed from cart.");
    }
}
