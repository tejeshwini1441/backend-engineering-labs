package com.backendMiniProject.OrderAndInventoryManagement.controller;

import com.backendMiniProject.OrderAndInventoryManagement.dto.*;
import com.backendMiniProject.OrderAndInventoryManagement.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/inventory")
@Slf4j
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponseDto> getInventoryByProductId(@PathVariable Long productId){
        return ResponseEntity.ok(inventoryService.getInventoryByProductId(productId));
    }

    @GetMapping
    public ResponseEntity<List<InventoryPageResponseDto>> allInventories(){
        return ResponseEntity.ok(inventoryService.allInventories());
    }
    @PostMapping
    public ResponseEntity<InventoryResponseDto> createInventory(@RequestBody InventoryRequestDto inventoryRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createInventory(inventoryRequestDto));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<InventoryResponseDto> updateInventory(@RequestBody InventoryRequestDto inventoryRequestDto, @PathVariable Long productId){
        return ResponseEntity.ok(inventoryService.updateInventory(inventoryRequestDto, productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteInventory(@PathVariable long id){
        log.info("deleted ...");
        inventoryService.deleteInventory(id);
        log.info("deleted");
        return null;
    }

    @PostMapping("/{productid}/{quantity}")
    public ResponseEntity<InventoryResponseDto> decressStock(
            @PathVariable Long productid,
            @PathVariable Long quantity) {

        return inventoryService.decressStock(productid, quantity);
    }
}
