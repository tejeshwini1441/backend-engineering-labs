package com.backendMiniProject.OrderAndInventoryManagement.service;

import com.backendMiniProject.OrderAndInventoryManagement.dto.InventoryPageResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.InventoryRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.InventoryResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.Inventory;
import com.backendMiniProject.OrderAndInventoryManagement.entity.Product;
import com.backendMiniProject.OrderAndInventoryManagement.error.BadRequestException;
import com.backendMiniProject.OrderAndInventoryManagement.error.ResourceNotFoundException;
import com.backendMiniProject.OrderAndInventoryManagement.mapper.InventoryMapper;
import com.backendMiniProject.OrderAndInventoryManagement.repository.InventoryRepo;
import com.backendMiniProject.OrderAndInventoryManagement.repository.ProdRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepo inventoryRepo;
    private final InventoryMapper inventoryMapper;
    private final ProdRepo prodRepo;

    public InventoryResponseDto getInventoryByProductId(Long productId) {
        Inventory inventory=inventoryRepo.findByProductId(productId).orElseThrow(() -> new BadRequestException("prod not found"));

        return inventoryMapper.toDto(inventory);
    }

    public List<InventoryPageResponseDto> allInventories() {
        List<Inventory> inventories=inventoryRepo.findAllInventories();

        return inventoryMapper.toListOfDtoInventories(inventories);
    }

    public InventoryResponseDto createInventory(InventoryRequestDto inventoryRequestDto) {

        inventoryRepo.findByProductId(inventoryRequestDto.productId())
                .ifPresent(i -> {
                    throw new BadRequestException("Inventory already exists");
                });

        Product product = prodRepo.findById(inventoryRequestDto.productId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product doesn't exist",
                        inventoryRequestDto.productId()
                ));

        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setQuantity(inventoryRequestDto.quantity());

        inventoryRepo.save(inventory);

        return inventoryMapper.toDto(inventory);
    }

    public InventoryResponseDto updateInventory(InventoryRequestDto inventoryRequestDto, Long productId) {

        Inventory inventory=inventoryRepo.findByProductId(productId).orElseThrow(()-> new BadRequestException("inventory not found"));
        inventory.setQuantity(inventoryRequestDto.quantity());
        return inventoryMapper.toDto(inventory);
    }

    public void deleteInventory(long id) {
        Inventory inventory=inventoryRepo.findByProductId(id).orElseThrow(()-> new BadRequestException(" inventory doesnt exists"));
        inventoryRepo.delete(inventory);
        return;
    }

    @Transactional
    public ResponseEntity<InventoryResponseDto> decressStock(Long productid, Long quantity) {
        Inventory inventory=inventoryRepo.findByProductId(productid).orElseThrow(()-> new BadRequestException(" inventory doesnt exists"));

        if(inventory.getQuantity() < quantity){
            throw new BadRequestException("insufficent stock");
        }
        inventory.setQuantity((int) (inventory.getQuantity() - quantity));
        return null;
    }
}
