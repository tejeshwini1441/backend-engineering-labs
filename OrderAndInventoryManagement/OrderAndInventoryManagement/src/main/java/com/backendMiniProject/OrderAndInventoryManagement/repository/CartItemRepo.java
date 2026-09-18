package com.backendMiniProject.OrderAndInventoryManagement.repository;

import com.backendMiniProject.OrderAndInventoryManagement.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    @Query("""
            SELECT i from CartItem i
            where i.id = : cartId
            """)
    List<CartItem> findByCartId(Long cartId);

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
}
