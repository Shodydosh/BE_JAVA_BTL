package com.javaBTL.BE_JAVA_BTL.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use AUTO for UUID generation
    private UUID id;

    public Cart() {
        // Tạo một UUID mới khi tạo đối tượng Cart

        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartItem> items = new ArrayList<>();
    public void addItem(Product product) {
        items.add(new CartItem(product, 1));
    }

    public void updateItemQuantity(Product product, int quantity) {
        items.forEach(item -> {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(quantity);
            }
        });
    }

    public void clearCart() {
        items.clear();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void updateProductQuantity(UUID productId, int newQuantity) {
        items.forEach(item -> {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(newQuantity);
            }
        });
    }

    public int getCount() {
        return items.size();
    }

    public Double getTotal() {
        return items.stream().mapToDouble(CartItem::getTotal).sum();
    }
}

