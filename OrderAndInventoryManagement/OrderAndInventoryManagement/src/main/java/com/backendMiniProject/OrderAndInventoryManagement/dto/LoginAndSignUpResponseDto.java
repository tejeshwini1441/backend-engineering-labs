package com.backendMiniProject.OrderAndInventoryManagement.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginAndSignUpResponseDto(
        String name,
        String email,
        String accessToken
) {


}
