package com.javaBTL.BE_JAVA_BTL.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.service.ProductService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;
    //Add 1 product
    @PostMapping ("/add")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    // Upload multi product
    @PostMapping("/upload")
    public List<Product> uploadProducts(@RequestBody List<Product> products) {
        return productService.uploadProducts(products);
    }

    // Admin CRUD operations
    @PostMapping("/{id}")
    public Product createOrUpdateProduct(@PathVariable UUID id, @RequestBody Product product) {
        // Kiểm tra xem sản phẩm với ID đã cho có tồn tại hay không
        Product existingProduct = productService.getProductById(id);

        if (existingProduct == null) {
            // Nếu sản phẩm không tồn tại, tạo một sản phẩm mới với ID đã cho
            // và lưu nó vào cơ sở dữ liệu
            product.setId(id); // Đặt ID của sản phẩm
            return productService.createProduct(product);
        } else {
            // Cập nhật thông tin sản phẩm từ product
            existingProduct.setRetailer(product.getRetailer());
            existingProduct.setImg_url(product.getImg_url());
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setUrl(product.getUrl());
            existingProduct.setCategory(product.getCategory());

            // Lưu sản phẩm đã cập nhật vào cơ sở dữ liệu
            return productService.createOrUpdateProduct(id, existingProduct);
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok("Product with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

    }
    @DeleteMapping("/delete/all")
    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }
    @DeleteMapping("/delete/byCategory")
    public void deleteProductsByCategory(@RequestParam("category") String category) {
        productService.deleteProductsByCategory(category);
    }

}