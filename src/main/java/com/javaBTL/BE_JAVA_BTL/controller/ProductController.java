package com.javaBTL.BE_JAVA_BTL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.service.ProductService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get all laptops
    @GetMapping("/laptop")
    public List<Product> getAllLaptops() {
        return productService.getAllLaptops();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable UUID id) {
        return productService.getProductById(id);
    }
    //Search product by keyword
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam("keyword") String keyword) {
        return productService.searchProducts(keyword);
    }
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }
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
    // Create a new product
    @PostMapping ("/add")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }


}
