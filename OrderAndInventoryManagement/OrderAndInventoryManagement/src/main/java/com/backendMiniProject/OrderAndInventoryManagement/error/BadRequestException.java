package com.backendMiniProject.OrderAndInventoryManagement.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BadRequestException extends RuntimeException {

    String message;
    Long resourceId;

    public BadRequestException(String message){
        super(message);
        this.message=message;
    }

}