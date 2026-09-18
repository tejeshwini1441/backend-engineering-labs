package com.backendMiniProject.OrderAndInventoryManagement.dto;

public record InventoryPageResponseDto(
        Long id,
        String name,
        Double price,
        String description,
        Integer quantity
) {
}
