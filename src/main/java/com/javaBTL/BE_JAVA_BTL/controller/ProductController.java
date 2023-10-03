package com.javaBTL.BE_JAVA_BTL.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.service.ProductService;

@RestController
@RequestMapping ("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts () {
        List<Product> products = productService.getAllProducts ();
        return new ResponseEntity<List<Product>> (products, HttpStatus.OK);
    }

    // Get a product by id
    @GetMapping ("/{id}")
    public ResponseEntity<Product> getProductById (@PathVariable UUID id) {
        Product product = productService.getProductById (id);
        return new ResponseEntity<Product> (product, HttpStatus.OK);
    }

    // Add a new product
    @PostMapping
    public ResponseEntity<Product> addProduct (@RequestBody Product product) {
        productService.addProduct (product);
        return new ResponseEntity<Product> (product, HttpStatus.CREATED);
    }

    // Update an existing product
    @PutMapping ("/{id}")
    public ResponseEntity<Product> updateProduct (@PathVariable UUID id, @RequestBody Product product) {
        productService.updateProduct (id, product);
        return new ResponseEntity<Product> (product, HttpStatus.OK);
    }

    // Delete a product by id
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable UUID id) {
        productService.deleteProduct (id);
        return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
    }
}
