package com.backendMiniProject.OrderAndInventoryManagement.dto;

public record ProductCreateRequestDto(
        String name,
        Double price,
        String description
) {
}
