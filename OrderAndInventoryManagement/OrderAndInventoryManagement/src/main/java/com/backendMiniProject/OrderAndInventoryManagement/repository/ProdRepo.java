package com.backendMiniProject.OrderAndInventoryManagement.repository;

import com.backendMiniProject.OrderAndInventoryManagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdRepo extends JpaRepository<Product , Long> {
    @Query("""
            SELECT p FROM Product p
            WHERE p.id >: cursorKey
            ORDER by p.id
            LIMIT :limit
       """)
    List<Product> findAllPaginated(Long cursorKey, Long limit);
}

