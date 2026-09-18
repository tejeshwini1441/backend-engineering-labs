package com.backendMiniProject.OrderAndInventoryManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name="Cart_Item")
public class CartItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="cart_id")
    Cart cart;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id")
    Product product;

    @Column(nullable = false)
    Integer quantity;

    @CreationTimestamp
    LocalDateTime created_at;


}
