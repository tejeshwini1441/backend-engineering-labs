package com.backendMiniProject.OrderAndInventoryManagement.mapper;

import com.backendMiniProject.OrderAndInventoryManagement.dto.CartMessage;
import com.backendMiniProject.OrderAndInventoryManagement.dto.CartResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface CartMapper {

    CartResponseDto tolistofCartItems(Cart cart);

    CartMessage toStringValue(String a);
}
