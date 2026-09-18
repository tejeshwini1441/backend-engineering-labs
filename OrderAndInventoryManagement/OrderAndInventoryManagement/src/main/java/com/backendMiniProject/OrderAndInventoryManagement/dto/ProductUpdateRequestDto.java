package com.backendMiniProject.OrderAndInventoryManagement.dto;

public record ProductUpdateRequestDto(
        String name,
        Double price,
        String description
) {
}
