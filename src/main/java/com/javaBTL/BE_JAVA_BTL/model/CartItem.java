package com.javaBTL.BE_JAVA_BTL.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class CartItem implements Serializable {
    @Id
    private UUID id;

    @OneToOne // Use @OneToOne if each cart item is associated with one product
    private Product product;

    private int quantity;

    public CartItem() {
        this.id = UUID.randomUUID();
    }

    public CartItem(Product product, int quantity) {
        this.id = UUID.randomUUID();
        this.product = product;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return Double.parseDouble(product.getPrice()) * quantity;
    }
}
