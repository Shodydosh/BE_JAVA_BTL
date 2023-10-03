package com.javaBTL.BE_JAVA_BTL.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return (Product) productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(UUID id, Product updatedProduct) {
        Product existingProduct = getProductById(id);

        if (existingProduct != null) {
            existingProduct.setRetailer(updatedProduct.getRetailer());
            existingProduct.setImg_url(updatedProduct.getImg_url());
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setUrl(updatedProduct.getUrl());
            existingProduct.setCategory(updatedProduct.getCategory());

            return productRepository.save(existingProduct);
        }

        return null; // Return null if the product with the given ID doesn't exist
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
