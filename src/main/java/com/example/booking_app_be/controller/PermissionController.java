package com.example.booking_app_be.controller;

import com.example.booking_app_be.dto.request.PermissionRequest;
import com.example.booking_app_be.dto.response.ApiResponse;
import com.example.booking_app_be.dto.response.PermissionResponse;
import com.example.booking_app_be.service.PermissionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAllPermission() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .data(permissionService.getAllPermission())
                .build();
    }

    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody @Valid PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .data(permissionService.createPermission(request))
                .build();
    }

    @DeleteMapping("/{name}")
    ApiResponse<String> deletePermission(@PathVariable String name) {
        permissionService.deletePermission(name);
        return ApiResponse.<String>builder().data("Permission has been deleted").build();
    }
}
