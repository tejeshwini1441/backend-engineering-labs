package com.backendMiniProject.OrderAndInventoryManagement.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ResourceNotFoundException extends RuntimeException{

    String message;
    Long resourceId;

    public ResourceNotFoundException(String message, Long id){
        super(message);
        this.message=message;
        this.resourceId=id;
    }
}
