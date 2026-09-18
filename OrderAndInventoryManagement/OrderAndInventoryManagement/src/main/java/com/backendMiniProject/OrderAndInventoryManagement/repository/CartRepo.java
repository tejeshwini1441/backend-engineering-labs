package com.backendMiniProject.OrderAndInventoryManagement.repository;

import com.backendMiniProject.OrderAndInventoryManagement.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long id);
}
