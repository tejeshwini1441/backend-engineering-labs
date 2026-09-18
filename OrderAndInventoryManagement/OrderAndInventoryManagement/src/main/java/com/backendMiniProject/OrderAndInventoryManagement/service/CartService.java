package com.backendMiniProject.OrderAndInventoryManagement.service;

import com.backendMiniProject.OrderAndInventoryManagement.dto.CartMessage;
import com.backendMiniProject.OrderAndInventoryManagement.dto.CartRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.CartResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.Cart;
import com.backendMiniProject.OrderAndInventoryManagement.entity.CartItem;
import com.backendMiniProject.OrderAndInventoryManagement.entity.User;
import com.backendMiniProject.OrderAndInventoryManagement.error.BadRequestException;
import com.backendMiniProject.OrderAndInventoryManagement.mapper.CartMapper;
import com.backendMiniProject.OrderAndInventoryManagement.repository.CartRepo;
import com.backendMiniProject.OrderAndInventoryManagement.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepo cartRepo;
    private final CartMapper cartMapper;
    private final CartItemService cartItemService;
    private final UserRepo userRepo;

    public List<CartResponseDto> getCart(Long id) {

        Cart cart=cartRepo.findByUserId(id);
        return cartItemService.findItemOfCart(cart.getId());

    }

    public CartMessage deleteItemFromCart(Long id, CartRequestDto cartRequestDto) {
        Cart cart=cartRepo.findByUserId(id);

        if(cart==null){
            throw new BadRequestException("cart doesnt exists");
        }

        cartItemService.deleteItemFromCart(cart.getId(), cartRequestDto.product().getId());
        String msg="item deleted from cart";
        return cartMapper.toStringValue(msg);
    }
    
    public CartResponseDto saveToLoggedInCart(Long userId, Long guestId) {
        Cart guestCart=cartRepo.findByUserId(guestId);

        if(guestCart==null){
            throw new BadRequestException("no items in guest cart so nothing added to saved user card");
        }
        Cart userCart=cartRepo.findByUserId(userId);
        if(userCart==null){
            User user=userRepo.getReferenceById(userId);
            guestCart.setUser(user);
            cartRepo.save(guestCart);
        }else{
            cartItemService.
        }



    }

    public CartResponseDto updateCart(Long id, CartRequestDto cartRequestDto) {

    }

    public CartMessage addToCart(Long userId, CartRequestDto cartRequestDto) {
        Cart cart=cartRepo.findByUserId(userId);

        if(cart==null){
            Cart cart1=new Cart();
            User user=userRepo.getReferenceById(userId);
            cart1.setUser(user);
            cartRepo.save(cart1);
        }
        cartItemService.addToCart(cart.getId(), cartRequestDto.product().getId(), cartRequestDto.quantity());

        String a="added";
        return cartMapper.toStringValue(a);

    }
}
