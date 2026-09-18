package com.backendMiniProject.OrderAndInventoryManagement.service;

import com.backendMiniProject.OrderAndInventoryManagement.dto.LoginRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.LoginAndSignUpResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.SignUpRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.User;
import com.backendMiniProject.OrderAndInventoryManagement.enums.Role;
import com.backendMiniProject.OrderAndInventoryManagement.error.BadRequestException;
import com.backendMiniProject.OrderAndInventoryManagement.mapper.LoginOrSignUpResponseMapper;
import com.backendMiniProject.OrderAndInventoryManagement.repository.UserRepo;
import com.backendMiniProject.OrderAndInventoryManagement.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final LoginOrSignUpResponseMapper loginOrSignUpResponseMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    public LoginAndSignUpResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.email(),
                        loginRequestDto.password()
                )
        );
        User user=(User) authentication.getPrincipal();

        String accessToken= jwtUtil.generateAccessToken(user);

        return loginOrSignUpResponseMapper.toDto(user, accessToken);
    }

    public LoginAndSignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        Optional<User> existingUser =
                userRepo.findByEmail(signUpRequestDto.email());

        if (existingUser.isPresent()) {
            log.info("ayy im there okay");
            throw new BadRequestException("User already exists");
        }

        String password= signUpRequestDto.password();
        String hashedPassword= passwordEncoder.encode(password);

        User user=new User();
        user.setEmail(signUpRequestDto.email());
        user.setPassword(hashedPassword);
        user.setName(signUpRequestDto.name());
        user.setRole(Role.ROLE_CUSTOMER);

        // Save first so the generated ID is available
        userRepo.save(user);

        String accessToken = jwtUtil.generateAccessToken(user);

        return loginOrSignUpResponseMapper.toDto(user, accessToken);
    }

}
