package com.backendMiniProject.OrderAndInventoryManagement.controller;

import com.backendMiniProject.OrderAndInventoryManagement.dto.CartMessage;
import com.backendMiniProject.OrderAndInventoryManagement.dto.CartRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.CartResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.security.JwtUtil;
import com.backendMiniProject.OrderAndInventoryManagement.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final JwtUtil jwtUtil;
    private final

    @GetMapping
    public ResponseEntity<List<CartResponseDto>> getCart(@PathVariable(required=false) Long guestId){
        Long userId=jwtUtil.getUserId();
        Long id = userId==null ? guestId: userId;

        return ResponseEntity.ok(cartService.getCart(id));

    }

    @PostMapping("/")
    public ResponseEntity<CartMessage> addToCart(@PathVariable(required=false) Long guestId,
                                                 @RequestBody CartRequestDto cartRequestDto){
        Long userId=jwtUtil.getUserId();
        Long id = userId==null ? guestId: userId;

        return ResponseEntity.ok(cartService.addToCart(id, cartRequestDto));
    }

    @PatchMapping
    public ResponseEntity<CartResponseDto> updateCart(@PathVariable(required=false) Long guestId,
                                                      @RequestBody CartRequestDto cartRequestDto){
        Long userId=jwtUtil.getUserId();
        Long id = userId==null ? guestId: userId;

        return ResponseEntity.ok(cartService.updateCart(id, cartRequestDto));
    }

    @DeleteMapping
    public ResponseEntity<CartMessage> deleteItemFromCart(@PathVariable(required=false) Long guestId,
                           @RequestBody CartRequestDto cartRequestDto){
        Long userId=jwtUtil.getUserId();
        Long id = userId==null ? guestId: userId;

        return ResponseEntity.ok(cartService.deleteItemFromCart(id, cartRequestDto));
    }

    @PostMapping("/save")
    public ResponseEntity<CartResponseDto> saveCart(@PathVariable(required=true) Long guestId){
        Long userId=jwtUtil.getUserId();

        return ResponseEntity.ok(cartService.saveToLoggedInCart(userId, guestId));
    }





}
