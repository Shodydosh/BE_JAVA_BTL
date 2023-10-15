package com.javaBTL.BE_JAVA_BTL.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartItem> items = new ArrayList<>();

    public Cart() {
        // Tạo một UUID mới khi tạo đối tượng Cart
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void addItem(Product product) {

        // Check if the product is already in the cart

        if (items.stream().anyMatch(item -> item.getProduct().getId().equals(product.getId()))) {
            updateItemQuantity(product, items.stream().filter(item -> item.getProduct().getId().equals(product.getId())).findFirst().get().getQuantity() + 1);
        }

        // Add the product to the cart
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItem(product, 1));
    }

    public void updateItemQuantity(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    public void clearCart() {
        items.clear();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public Double getTotal() {
        return items.stream().mapToDouble(CartItem::getTotal).sum();
    }

    public void updateProductQuantity(UUID productId, int newQuantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(newQuantity);
                return;
            }
        }
    }

    public void removeItem(UUID productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public void setItems(ArrayList<CartItem> arrayList) {
        items = arrayList;
    }
}
