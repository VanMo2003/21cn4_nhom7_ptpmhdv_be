package com.example.booking_app_be.service;

import com.example.booking_app_be.dto.request.UserCreationRequest;
import com.example.booking_app_be.dto.response.UserResponse;
import com.example.booking_app_be.entity.User;
import com.example.booking_app_be.exception.AppException;
import com.example.booking_app_be.exception.ErrorCode;
import com.example.booking_app_be.mapper.UserMapper;
import com.example.booking_app_be.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;


    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);

        return userMapper.toUserResponse(userRepository.save(user));
    }
}
