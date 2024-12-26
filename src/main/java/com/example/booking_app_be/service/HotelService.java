package com.example.booking_app_be.service;


import com.example.booking_app_be.dto.response.HotelResponse;
import com.example.booking_app_be.dto.response.RoomResponse;
import com.example.booking_app_be.dto.response.ServiceResponse;
import com.example.booking_app_be.entity.Hotel;
import com.example.booking_app_be.exception.AppException;
import com.example.booking_app_be.exception.ErrorCode;
import com.example.booking_app_be.mapper.HotelMapper;
import com.example.booking_app_be.mapper.UserMapper;
import com.example.booking_app_be.repository.HotelRepository;
import com.example.booking_app_be.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelService {
    HotelRepository hotelRepository;
    ServiceService serviceService;
    RoomService roomService;
    HotelMapper hotelMapper;

    public HotelResponse getHotelById(Long id){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_EXISTED));

        List<ServiceResponse> serviceResponses = serviceService.getAllByHotel(id);
        List<RoomResponse> roomResponses = roomService.getAllByHotel(id);

        HotelResponse hotelResponse = hotelMapper.toHotelResponse(hotel);
        hotelResponse.setServices(serviceResponses);
        hotelResponse.setRooms(roomResponses);

        return  hotelResponse;
    }
}
