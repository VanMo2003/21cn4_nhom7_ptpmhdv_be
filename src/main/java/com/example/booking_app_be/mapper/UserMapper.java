package com.example.booking_app_be.mapper;

import com.example.booking_app_be.dto.request.UserCreationRequest;
import com.example.booking_app_be.dto.response.UserResponse;
import com.example.booking_app_be.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", ignore = true)
    User toUser(UserCreationRequest request);

    @Mapping(target = "role", ignore = true)
    UserResponse toUserResponse(User user);
}
//da fix