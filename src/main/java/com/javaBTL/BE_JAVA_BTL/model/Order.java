package com.javaBTL.BE_JAVA_BTL.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private Double totalAmount;
    private String shippingAddress;
    private String phoneNumber;
    private String paymentMethod;
    private String cardNumber;
    private String cardHolder;
    private String status = "PENDING";

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}