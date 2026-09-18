package com.backendMiniProject.OrderAndInventoryManagement.dto;

import com.backendMiniProject.OrderAndInventoryManagement.entity.Product;

public record CartRequestDto(
        Product product,
        Integer quantity
) {
}
