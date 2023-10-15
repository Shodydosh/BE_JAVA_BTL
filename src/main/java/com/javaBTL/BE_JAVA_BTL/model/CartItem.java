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

    public CartItem() {
        this.id = UUID.randomUUID();
    }
    public CartItem(Product product, int quantity) {
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

    public int getQuantity() {
        return quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
