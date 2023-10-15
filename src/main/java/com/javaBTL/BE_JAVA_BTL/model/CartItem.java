package com.javaBTL.BE_JAVA_BTL.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class CartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private Product product;

    private int quantity;

    @ManyToOne // Sử dụng @ManyToOne để định nghĩa quan hệ với thực thể Cart
    @JoinColumn(name = "cart_id") // Định nghĩa khóa ngoại (foreign key) tới Cart
    private Cart cart; // Thêm trường cart để thiết lập quan hệ ngược lại

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters và setters

    public double getTotal() {
        // Assuming that "getPrice" returns a numeric value (e.g., a double or BigDecimal)
        return Double.parseDouble(product.getPrice()) * quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
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
}
