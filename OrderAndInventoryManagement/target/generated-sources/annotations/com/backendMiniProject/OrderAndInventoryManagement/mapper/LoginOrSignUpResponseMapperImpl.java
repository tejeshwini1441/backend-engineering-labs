package com.backendMiniProject.OrderAndInventoryManagement.mapper;

import com.backendMiniProject.OrderAndInventoryManagement.dto.LoginAndSignUpResponseDto;
import com.backendMiniProject.OrderAndInventoryManagement.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-16T12:34:09+0530",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class LoginOrSignUpResponseMapperImpl implements LoginOrSignUpResponseMapper {

    @Override
    public LoginAndSignUpResponseDto toDto(User user, String accessToken) {
        if ( user == null && accessToken == null ) {
            return null;
        }

        String name = null;
        String email = null;
        if ( user != null ) {
            name = user.getName();
            email = user.getEmail();
        }
        String accessToken1 = null;
        accessToken1 = accessToken;

        LoginAndSignUpResponseDto loginAndSignUpResponseDto = new LoginAndSignUpResponseDto( name, email, accessToken1 );

        return loginAndSignUpResponseDto;
    }
}
