package com.backendMiniProject.OrderAndInventoryManagement.dto;

import java.time.Instant;

public record OrderResponse(
        Long orderId,
        Instant placedTime,
        Double totalAmount
) {
}
