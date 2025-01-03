package com.example.booking_app_be.mapper;

import com.example.booking_app_be.dto.request.RoleRequest;
import com.example.booking_app_be.dto.response.RoleResponse;
import com.example.booking_app_be.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
