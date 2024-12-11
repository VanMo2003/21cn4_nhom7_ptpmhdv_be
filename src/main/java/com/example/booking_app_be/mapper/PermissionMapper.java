package com.example.booking_app_be.mapper;

import com.example.booking_app_be.dto.request.PermissionRequest;
import com.example.booking_app_be.dto.response.PermissionResponse;
import com.example.booking_app_be.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
