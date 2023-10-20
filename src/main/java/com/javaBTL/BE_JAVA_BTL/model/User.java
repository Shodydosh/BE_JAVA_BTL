package com.javaBTL.BE_JAVA_BTL.model;

import jakarta.persistence.*;

import java.util.UUID; // Import the UUID class

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Use AUTO for UUID generation
    private UUID id; // Change id to UUID
    private String name;
    private String email;
    private String password; // Add password field
    private String role;
    @OneToOne(mappedBy = "user")
    private Cart cart;
    public User() {
        this.role = "client"; // Set the default role to "client" in the constructor
        this.cart = new Cart(); // Create a new cart for the user
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }
}
