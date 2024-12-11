package com.example.booking_app_be.service;

import com.example.booking_app_be.dto.request.PermissionRequest;
import com.example.booking_app_be.dto.response.PermissionResponse;
import com.example.booking_app_be.entity.Permission;
import com.example.booking_app_be.mapper.PermissionMapper;
import com.example.booking_app_be.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    private static final Logger log = LoggerFactory.getLogger(PermissionService.class);
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public List<PermissionResponse> getAllPermission() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    public void deletePermission(String name) {
        permissionRepository.deleteById(name);
    }
}
