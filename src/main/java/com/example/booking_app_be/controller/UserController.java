package com.example.booking_app_be.controller;

import com.example.booking_app_be.dto.request.UserCreationRequest;
import com.example.booking_app_be.dto.request.UserUpdateRequest;
import com.example.booking_app_be.dto.response.ApiResponse;
import com.example.booking_app_be.dto.response.UserResponse;
import com.example.booking_app_be.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setData(userService.createUser(request));
        return response;
    }
    @PutMapping("/updateMyInfo")
    ApiResponse<UserResponse> updateMyInfo(@RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder().data(userService.updateMyInfo(request)).build();
    }
}

