package com.backendMiniProject.OrderAndInventoryManagement.mapper;

import com.backendMiniProject.OrderAndInventoryManagement.dto.PaginatedProductResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.ProductResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.ProductUpdateRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-17T12:24:28+0530",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponseDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Double price = null;
        String description = null;

        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        description = product.getDescription();

        ProductResponseDto productResponseDto = new ProductResponseDto( id, name, price, description );

        return productResponseDto;
    }

    @Override
    public void updateProduct(ProductUpdateRequestDto request, Product product) {
        if ( request == null ) {
            return;
        }

        if ( request.name() != null ) {
            product.setName( request.name() );
        }
        if ( request.price() != null ) {
            product.setPrice( request.price() );
        }
        if ( request.description() != null ) {
            product.setDescription( request.description() );
        }
    }

    @Override
    public PaginatedProductResponseDto toPaginatedResponse(List<Product> products, Long cursorKey) {
        if ( products == null && cursorKey == null ) {
            return null;
        }

        List<ProductResponseDto> products1 = null;
        products1 = productListToProductResponseDtoList( products );
        Long cursorKey1 = null;
        cursorKey1 = cursorKey;

        PaginatedProductResponseDto paginatedProductResponseDto = new PaginatedProductResponseDto( products1, cursorKey1 );

        return paginatedProductResponseDto;
    }

    protected List<ProductResponseDto> productListToProductResponseDtoList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductResponseDto> list1 = new ArrayList<ProductResponseDto>( list.size() );
        for ( Product product : list ) {
            list1.add( toDto( product ) );
        }

        return list1;
    }
}
