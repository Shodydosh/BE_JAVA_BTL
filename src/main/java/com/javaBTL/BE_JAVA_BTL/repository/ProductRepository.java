package com.javaBTL.BE_JAVA_BTL.repository;

import com.javaBTL.BE_JAVA_BTL.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public Product save(Product product) {
        // If the product already has an ID, it's considered an update
        if (product.getId() != null) {
            int index = findProductIndexById(product.getId());
            if (index != -1) {
                products.set(index, product);
            }
        } else {
            // Generate a new UUID for a new product
            product.setId(UUID.randomUUID());
            products.add(product);
        }
        return product;
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(UUID id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public void deleteById(UUID id) {
        int index = findProductIndexById(id);
        if (index != -1) {
            products.remove(index);
        }
    }

    private int findProductIndexById(UUID id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1; // Product with the given ID not found
    }
}
