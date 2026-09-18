package com.backendMiniProject.OrderAndInventoryManagement.service;

import com.backendMiniProject.OrderAndInventoryManagement.dto.CartMessage;
import com.backendMiniProject.OrderAndInventoryManagement.dto.CartResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.Cart;
import com.backendMiniProject.OrderAndInventoryManagement.entity.CartItem;
import com.backendMiniProject.OrderAndInventoryManagement.entity.Product;
import com.backendMiniProject.OrderAndInventoryManagement.error.BadRequestException;
import com.backendMiniProject.OrderAndInventoryManagement.error.ResourceNotFoundException;
import com.backendMiniProject.OrderAndInventoryManagement.mapper.CartItemMapper;
import com.backendMiniProject.OrderAndInventoryManagement.repository.CartItemRepo;
import com.backendMiniProject.OrderAndInventoryManagement.repository.CartRepo;
import com.backendMiniProject.OrderAndInventoryManagement.repository.ProdRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepo cartItemRepo;
    private final CartItemMapper cartItemMapper;
    private final ProdRepo prodRepo;
    private final CartRepo cartRepo;

    public List<CartResponseDto> findItemOfCart(Long cartId){
        List<CartItem> items=cartItemRepo.findByCartId(cartId);
        return cartItemMapper.toListDto(items);
    }

    public void addToCart(Long cartId, Long productId, Integer quantity) {

        CartItem cartItem=new CartItem();

        Cart cart=cartRepo.getReferenceById(cartId);
        cartItem.setCart(cart);

        Product product=prodRepo.getReferenceById(productId);
        cartItem.setProduct(product);

        cartItem.setQuantity(quantity);

        cartItemRepo.save(cartItem);

    }

    public void deleteItemFromCart(Long cartId, Long productId) {
        CartItem cartItem = cartItemRepo.findByCartIdAndProductId(cartId, productId).orElseThrow(() -> new ResourceNotFoundException("Cart Item", productId));

        cartItemRepo.delete(cartItem);

    }
}
