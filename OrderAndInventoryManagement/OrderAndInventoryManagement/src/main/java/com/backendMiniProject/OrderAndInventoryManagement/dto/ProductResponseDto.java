package com.backendMiniProject.OrderAndInventoryManagement.dto;

public record ProductResponseDto(
        Long id,
        String name,
        Double price,
        String description
) {
}
