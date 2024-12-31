package com.example.booking_app_be.service;


import com.example.booking_app_be.dto.request.HotelRequest;
import com.example.booking_app_be.dto.response.HotelResponse;
import com.example.booking_app_be.dto.response.RoomResponse;
import com.example.booking_app_be.dto.response.ServiceResponse;
import com.example.booking_app_be.entity.Hotel;
import com.example.booking_app_be.entity.User;
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
    UserRepository userRepository;
    ServiceService serviceService;
    RoomService roomService;
    HotelMapper hotelMapper;
    UserMapper userMapper;

    public List<HotelResponse> getAllHotel() {


        return hotelRepository.findAll().stream()
                .map(hotel -> hotelMapper.toHotelResponse(hotel))
                .toList();
    }

    public HotelResponse createHotel(HotelRequest request) {
        User user = userRepository.findById(request.getUserID()).orElseThrow(() -> {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        });

        if (hotelRepository.existsByUserId(request.getUserID())) throw new AppException(ErrorCode.USER_EXISTED);

        Hotel hotel = hotelMapper.toHotel(request);

        hotel.setUser(user);

        HotelResponse hotelResponse = hotelMapper.toHotelResponse(hotelRepository.save(hotel));
        hotelResponse.setUser(userMapper.toUserResponse(user));

        return hotelResponse;
    }
    public HotelResponse getHotelById(Long id){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_EXISTED));

        List<ServiceResponse> serviceResponses = serviceService.getAllByHotel(id);
        List<RoomResponse> roomResponses = roomService.getAllByHotel(id);

        HotelResponse hotelResponse = hotelMapper.toHotelResponse(hotel);
        hotelResponse.setServices(serviceResponses);
        hotelResponse.setRooms(roomResponses);

        return  hotelResponse;
    }
    public HotelResponse updateHotel(Long id, HotelRequest request) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_EXISTED));

        hotelMapper.updateHotel(hotel, request);

        User user;
        if (!Objects.isNull(request.getUserID())) {
            user = userRepository.findById(request.getUserID()).orElseThrow(() -> {
                throw new AppException(ErrorCode.USER_NOT_EXISTED);
            });
            hotel.setUser(user);
        }

        HotelResponse hotelResponse = hotelMapper.toHotelResponse(hotelRepository.save(hotel));
        hotelResponse.setUser(userMapper.toUserResponse(hotel.getUser()));

        return hotelResponse;
    }


    @PreAuthorize("hasRole('ADMIN')")
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
    public List<HotelResponse> searchHotelByName(String name) {
        Specification<Hotel> spec = UserSpecification.hasSimilarName(name);
        List<Hotel> hotels = hotelRepository.findAll(spec);
        List<HotelResponse> hotelResponses =
                hotels.stream().map(hotel -> hotelMapper.toHotelResponse(hotel)).toList();

        return hotelResponses;
    }

    public List<HotelResponse> searchHotelByAddress(String address) {
        Specification<Hotel> spec = UserSpecification.hasSimilarAddress(address);
        List<Hotel> hotels = hotelRepository.findAll(spec);
        List<HotelResponse> hotelResponses =
                hotels.stream().map(hotel -> hotelMapper.toHotelResponse(hotel)).toList();

        return hotelResponses;
    }

    public List<HotelResponse> searchUsersByNameAndAddress(String name, String address) {
        Specification<Hotel> spec = UserSpecification.hasSimilarNameAndAddress(name, address);
        return hotelRepository.findAll(spec).stream()
                .map(hotel -> hotelMapper.toHotelResponse(hotel))
                .toList();
    }
}
