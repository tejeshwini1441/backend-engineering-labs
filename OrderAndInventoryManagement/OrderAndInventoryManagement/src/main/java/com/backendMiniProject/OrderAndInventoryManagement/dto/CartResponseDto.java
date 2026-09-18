package com.backendMiniProject.OrderAndInventoryManagement.dto;

import com.backendMiniProject.OrderAndInventoryManagement.entity.CartItem;

import java.util.List;

public record CartResponseDto(
        String name,
        Double price,
        Integer quantity
) {
}
