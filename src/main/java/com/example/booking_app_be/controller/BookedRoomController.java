package com.example.booking_app_be.controller;

import com.example.booking_app_be.dto.request.BookRequest;
import com.example.booking_app_be.dto.response.ApiResponse;
import com.example.booking_app_be.dto.response.BookedRoomResponse;
import com.example.booking_app_be.service.BookedRoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookedRoom")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookedRoomController {
    BookedRoomService bookedRoomService;

    @PostMapping
    ApiResponse<BookedRoomResponse> createHotel(@RequestBody BookRequest request) {
        return ApiResponse.<BookedRoomResponse>builder()
                .data(bookedRoomService.createBookedRoom(request))
                .build();
    }
}
