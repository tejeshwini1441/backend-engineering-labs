package com.backendMiniProject.OrderAndInventoryManagement.controller;

import com.backendMiniProject.OrderAndInventoryManagement.dto.OrderResponse;
import com.backendMiniProject.OrderAndInventoryManagement.dto.OrderUpdateResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.security.JwtUtil;
import com.backendMiniProject.OrderAndInventoryManagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getOrdersOfUser(){
        Long userId=jwtUtil.getUserId();

        return ResponseEntity.ok(orderService.getOrdersOfUser(userId));
    }

    @GetMapping("/admin/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        return ResponseEntity.ok(orderService.getALlOrders());
    }

    @
    @PatchMapping("/admin/orders")
    public ResponseEntity<OrderUpdateResponseDto> updateOrder(){
        return ResponseEntity.ok(orderService.updateOrder());
    }

    @DeleteMapping("/admin/orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return null;
    }
}
