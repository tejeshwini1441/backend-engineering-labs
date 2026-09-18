package com.backendMiniProject.OrderAndInventoryManagement.dto;

import java.util.List;

public record PaginatedProductResponseDto(
        List<ProductResponseDto> products,
        Long cursorKey
) {
}
