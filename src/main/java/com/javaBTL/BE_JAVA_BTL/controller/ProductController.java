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

    // Admin CRUD operations
    @PostMapping("/{id}")
    public Product createOrUpdateProduct(@PathVariable UUID id, @RequestBody Product product) {
        // You can implement create or update logic here
        return productService.createOrUpdateProduct(id, product);
    }

    // Create a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

}
