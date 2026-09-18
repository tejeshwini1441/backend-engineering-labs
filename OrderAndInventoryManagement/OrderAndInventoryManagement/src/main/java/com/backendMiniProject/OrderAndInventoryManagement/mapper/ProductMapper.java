package com.backendMiniProject.OrderAndInventoryManagement.mapper;

import com.backendMiniProject.OrderAndInventoryManagement.dto.PaginatedProductResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.ProductResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.ProductUpdateRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto toDto(Product product);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    void updateProduct(
            ProductUpdateRequestDto request,
            @MappingTarget Product product
    );

    PaginatedProductResponseDto toPaginatedResponse(List<Product> products, Long cursorKey);
}
