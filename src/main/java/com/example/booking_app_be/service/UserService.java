package com.example.booking_app_be.service;

import com.example.booking_app_be.dto.request.UserCreationRequest;
import com.example.booking_app_be.dto.request.UserUpdateRequest;
import com.example.booking_app_be.dto.response.UserResponse;
import com.example.booking_app_be.entity.Role;
import com.example.booking_app_be.entity.User;
import com.example.booking_app_be.exception.AppException;
import com.example.booking_app_be.exception.ErrorCode;
import com.example.booking_app_be.mapper.RoleMapper;
import com.example.booking_app_be.mapper.UserMapper;
import com.example.booking_app_be.repository.RoleRepository;
import com.example.booking_app_be.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    RoleMapper roleMapper;


    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = roleRepository.findById(request.getRole()).orElseThrow();

        user.setRole(role);

        UserResponse userResponse = userMapper.toUserResponse(userRepository.save(user));
        userResponse.setRole(roleMapper.toRoleResponse(role));

        return userResponse;
    }

    public UserResponse updateMyInfo(UserUpdateRequest request) {
    }
}
//da fix