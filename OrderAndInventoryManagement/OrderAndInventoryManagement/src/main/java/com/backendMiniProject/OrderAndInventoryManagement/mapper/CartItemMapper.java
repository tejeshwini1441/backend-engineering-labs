package com.backendMiniProject.OrderAndInventoryManagement.mapper;

import com.backendMiniProject.OrderAndInventoryManagement.dto.CartResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.CartItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    List<CartResponseDto> toListDto(List<CartItem> items);

    Cart
}
