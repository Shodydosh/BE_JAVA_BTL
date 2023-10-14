package com.javaBTL.BE_JAVA_BTL.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class CartItem implements Serializable {
    @Id
    @ManyToOne // You might need to adjust this based on your exact use case
    private Product product;

    private int quantity;

    public CartItem() {
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
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
