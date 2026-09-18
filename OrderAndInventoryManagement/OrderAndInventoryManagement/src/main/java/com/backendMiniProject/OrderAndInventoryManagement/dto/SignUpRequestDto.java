package com.backendMiniProject.OrderAndInventoryManagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

public record SignUpRequestDto(
        @NotBlank
        String name,

        @NonNull
        @Email
        @NotBlank
        String email,

        @NonNull
        String password

) {
}
