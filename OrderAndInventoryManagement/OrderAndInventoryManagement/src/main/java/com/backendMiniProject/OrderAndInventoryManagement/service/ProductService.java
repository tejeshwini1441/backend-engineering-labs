package com.backendMiniProject.OrderAndInventoryManagement.service;

import com.backendMiniProject.OrderAndInventoryManagement.dto.PaginatedProductResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.ProductCreateRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.ProductResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.ProductUpdateRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.Inventory;
import com.backendMiniProject.OrderAndInventoryManagement.entity.Product;
import com.backendMiniProject.OrderAndInventoryManagement.error.BadRequestException;
import com.backendMiniProject.OrderAndInventoryManagement.mapper.ProductMapper;
import com.backendMiniProject.OrderAndInventoryManagement.repository.InventoryRepo;
import com.backendMiniProject.OrderAndInventoryManagement.repository.ProdRepo;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProdRepo prodRepo;
    private final ProductMapper productMapper;
    private final InventoryRepo inventoryRepo;

    public ProductResponseDto getProductById(Long id) {
        Product product=prodRepo.findById(id).orElseThrow(() -> new BadRequestException("product not found"));

        return productMapper.toDto(product);
    }

    public PaginatedProductResponseDto getAllProducts(Long cursorKey, Long limit) {
        List<Product> allProducts= prodRepo.findAllPaginated(cursorKey, limit);

        return productMapper.toPaginatedResponse(allProducts, allProducts.getLast().getId());
    }

    @PreAuthorize("hasAuthority('INVENTORY_CREATE')")
    public ProductResponseDto createProduct(ProductCreateRequestDto productCreateRequestDto) {
        Product product=new Product();

        product.setName(productCreateRequestDto.name());
        product.setPrice(productCreateRequestDto.price());
        product.setDescription(productCreateRequestDto.description());

        prodRepo.save(product);

        return productMapper.toDto(product);
    }

    @PreAuthorize("hasAuthority('INVENTORY_UPDATE')")
    public ProductResponseDto updateProduct(ProductUpdateRequestDto productUpdateRequestDto, Long id) {
        Product product=prodRepo.findById(id).orElseThrow(() -> new BadRequestException(" prod not found"));

        productMapper.updateProduct(productUpdateRequestDto, product);
        prodRepo.save(product);
        return productMapper.toDto(product);
    }

    @PreAuthorize("hasAuthority('INVENTORY_DELETE')")
    public void deleteProduct(Long id) {
        Product product=prodRepo.findById(id).orElseThrow(() -> new BadRequestException("prod not found"));
        log.info(" in the service deleted");
        prodRepo.delete(product);
        return;
    }


}
