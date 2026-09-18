package com.backendMiniProject.OrderAndInventoryManagement.controller;

import com.backendMiniProject.OrderAndInventoryManagement.dto.PaginatedProductResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.ProductCreateRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.ProductResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.ProductUpdateRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/products")
    public ResponseEntity<PaginatedProductResponseDto> getAllProducts(@RequestParam(required= false) Long cursorKey, @RequestParam(defaultValue = "10") Long limit){
        return ResponseEntity.ok(productService.getAllProducts(cursorKey, limit));
    }

    @PostMapping("/admin/create")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductCreateRequestDto productCreateRequestDto){
        return ResponseEntity.ok(productService.createProduct(productCreateRequestDto));
    }

    @PatchMapping("/admin/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductUpdateRequestDto productUpdateRequestDto, @PathVariable Long id){
        return ResponseEntity.ok(productService.updateProduct(productUpdateRequestDto, id));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id){
        log.info("deleted ...");
        productService.deleteProduct(id);
        log.info("deleted");
        return null;
    }
}
