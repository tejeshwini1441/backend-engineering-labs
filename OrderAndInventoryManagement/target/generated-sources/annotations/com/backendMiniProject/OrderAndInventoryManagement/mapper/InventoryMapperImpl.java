package com.backendMiniProject.OrderAndInventoryManagement.mapper;

import com.backendMiniProject.OrderAndInventoryManagement.dto.InventoryPageResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.InventoryResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.Inventory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-18T11:42:30+0530",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class InventoryMapperImpl implements InventoryMapper {

    @Override
    public InventoryResponseDto toDto(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        Integer quantity = null;

        quantity = inventory.getQuantity();

        Long productId = null;

        InventoryResponseDto inventoryResponseDto = new InventoryResponseDto( productId, quantity );

        return inventoryResponseDto;
    }

    @Override
    public List<InventoryPageResponseDto> toListOfDtoInventories(List<Inventory> inventoryList) {
        if ( inventoryList == null ) {
            return null;
        }

        List<InventoryPageResponseDto> list = new ArrayList<InventoryPageResponseDto>( inventoryList.size() );
        for ( Inventory inventory : inventoryList ) {
            list.add( inventoryToInventoryPageResponseDto( inventory ) );
        }

        return list;
    }

    protected InventoryPageResponseDto inventoryToInventoryPageResponseDto(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        Long id = null;
        Integer quantity = null;

        id = inventory.getId();
        quantity = inventory.getQuantity();

        String name = null;
        Double price = null;
        String description = null;

        InventoryPageResponseDto inventoryPageResponseDto = new InventoryPageResponseDto( id, name, price, description, quantity );

        return inventoryPageResponseDto;
    }
}
