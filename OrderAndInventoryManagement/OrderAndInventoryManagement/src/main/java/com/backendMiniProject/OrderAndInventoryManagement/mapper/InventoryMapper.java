package com.backendMiniProject.OrderAndInventoryManagement.mapper;

import com.backendMiniProject.OrderAndInventoryManagement.dto.InventoryPageResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.InventoryResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.Inventory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface InventoryMapper {

    InventoryResponseDto toDto(Inventory inventory);

    List<InventoryPageResponseDto> toListOfDtoInventories (List<Inventory> inventoryList);
}
