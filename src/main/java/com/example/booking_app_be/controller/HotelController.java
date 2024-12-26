package com.example.booking_app_be.controller;


import com.example.booking_app_be.dto.response.ApiResponse;
import com.example.booking_app_be.dto.response.HotelResponse;
import com.example.booking_app_be.service.HotelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelController {
    HotelService hotelService;

    @GetMapping("/{id}")
    ApiResponse<HotelResponse> getHotelById(@PathVariable Long id) {
        return ApiResponse.<HotelResponse>builder()
                .data(hotelService.getHotelById(id))
                .build();
    }

}
