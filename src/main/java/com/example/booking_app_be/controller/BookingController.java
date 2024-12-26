package com.example.booking_app_be.controller;

import com.example.booking_app_be.dto.response.ApiResponse;
import com.example.booking_app_be.dto.response.BookingResponse;
import com.example.booking_app_be.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {
    BookingService bookingService;

    @GetMapping("/{userId}")
    ApiResponse<List<BookingResponse>> getBookingByUser(@PathVariable String userId) {
        return ApiResponse.<List<BookingResponse>>builder()
                .data(bookingService.getBookingByUser(userId))
                .build();
    }

}