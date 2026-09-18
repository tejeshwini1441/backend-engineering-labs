package com.backendMiniProject.OrderAndInventoryManagement.mapper;

import com.backendMiniProject.OrderAndInventoryManagement.dto.LoginAndSignUpResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginOrSignUpResponseMapper {
    LoginAndSignUpResponseDto toDto(User user, String accessToken);

}
