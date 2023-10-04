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


    // Admin CRUD operations
    @PostMapping("/{id}")
    public Product createOrUpdateProduct(@PathVariable UUID id, @RequestBody Product product) {
        // You can implement create or update logic here
        return productService.createOrUpdateProduct(id, product);
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
