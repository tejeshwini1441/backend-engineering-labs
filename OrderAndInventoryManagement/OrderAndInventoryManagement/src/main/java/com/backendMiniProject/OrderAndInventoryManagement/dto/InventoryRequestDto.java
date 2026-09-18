package com.backendMiniProject.OrderAndInventoryManagement.dto;

import com.backendMiniProject.OrderAndInventoryManagement.entity.Product;

public record InventoryRequestDto(
        Long productId,
        Integer quantity
){
}
