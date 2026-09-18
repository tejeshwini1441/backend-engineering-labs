package com.backendMiniProject.OrderAndInventoryManagement.security;

import com.backendMiniProject.OrderAndInventoryManagement.entity.User;
import com.backendMiniProject.OrderAndInventoryManagement.error.BadRequestException;
import com.backendMiniProject.OrderAndInventoryManagement.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {

        return userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new BadRequestException("User not found"));
    }
}