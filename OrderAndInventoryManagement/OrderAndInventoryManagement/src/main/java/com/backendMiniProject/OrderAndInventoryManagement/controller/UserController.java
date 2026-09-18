package com.backendMiniProject.OrderAndInventoryManagement.controller;

import com.backendMiniProject.OrderAndInventoryManagement.dto.LoginRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.LoginAndSignUpResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.dto.SignUpRequestDto;
import com.backendMiniProject.OrderAndInventoryManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    ResponseEntity<LoginAndSignUpResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        log.info("login request for user{}", loginRequestDto.email());
        LoginAndSignUpResponseDto loginAndSignUpResponseDto=userService.login(loginRequestDto);

        String accessToken=loginAndSignUpResponseDto.accessToken();
        ResponseCookie cookie= ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ofHours(1))
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(loginAndSignUpResponseDto);
    }

    @PostMapping("/signUp")
    ResponseEntity<LoginAndSignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        log.info("login request for user{}", signUpRequestDto.email());
        LoginAndSignUpResponseDto loginAndSignUpResponseDto=userService.signUp(signUpRequestDto);

        String accessToken=loginAndSignUpResponseDto.accessToken();
        ResponseCookie cookie= ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ofHours(1))
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(loginAndSignUpResponseDto);
    }
}
