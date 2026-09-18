package com.backendMiniProject.OrderAndInventoryManagement.dto;

import com.backendMiniProject.OrderAndInventoryManagement.enums.OrderStatus;

public record OrderStatusUpdateRequest(
        Long orderItemId,
        OrderStatus deliveryStatus
) {
}
