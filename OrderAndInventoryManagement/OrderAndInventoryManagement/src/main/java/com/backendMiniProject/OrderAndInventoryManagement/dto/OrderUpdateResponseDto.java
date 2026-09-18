package com.backendMiniProject.OrderAndInventoryManagement.dto;

import com.backendMiniProject.OrderAndInventoryManagement.enums.OrderStatus;

public record OrderUpdateResponseDto(
        Long orderId,
        OrderStatus status
) {
}
