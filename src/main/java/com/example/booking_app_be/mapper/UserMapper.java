package com.example.booking_app_be.mapper;

import com.example.booking_app_be.dto.request.UserCreationRequest;
import com.example.booking_app_be.dto.response.UserResponse;
import com.example.booking_app_be.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);
    //
    //    @Mapping(target = "roles", ignore = true)
    //    @Mapping(target = "password", ignore = true)
    //    void updateUser(@MappingTarget User user, UserUpdateRequest request);
    //
    //    @Mapping(target = "roles", ignore = true)
    //    void updateRole(@MappingTarget User user, UserUpdateRoleRequest request);

}
