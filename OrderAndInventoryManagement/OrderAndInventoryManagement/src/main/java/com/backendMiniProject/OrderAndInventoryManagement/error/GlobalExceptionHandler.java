package com.backendMiniProject.OrderAndInventoryManagement.error;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.security.SignatureException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex){
        ApiError apiError=new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        log.error(apiError.toString(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex){
        ApiError apiError=new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        log.error(apiError.toString(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiError> handleExpiredJwt(ExpiredJwtException ex) {
        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, "JWT token has expired");
        log.error(error.toString(), ex);
        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ApiError> handleMalformedJwt(MalformedJwtException ex) {
        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, "Invalid JWT token");
        log.error(error.toString(), ex);
        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiError> handleSignatureJwt(SignatureException ex) {
        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, "JWT signature validation failed");
        log.error(error.toString(), ex);
        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleGenericJwt(JwtException ex) {
        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, "JWT processing error");
        log.error(error.toString(), ex);
        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex) {
        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, "Authentication failed");
        log.error(error.toString(), ex);
        return ResponseEntity.status(error.status()).body(error);
    }
}
