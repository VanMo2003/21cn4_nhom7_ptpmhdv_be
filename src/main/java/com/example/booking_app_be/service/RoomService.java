package com.example.booking_app_be.service;

import com.example.booking_app_be.dto.request.RoomRequest;
import com.example.booking_app_be.dto.response.RoomResponse;
import com.example.booking_app_be.entity.Hotel;
import com.example.booking_app_be.entity.Room;
import com.example.booking_app_be.exception.AppException;
import com.example.booking_app_be.exception.ErrorCode;
import com.example.booking_app_be.mapper.RoomMapper;
import com.example.booking_app_be.repository.HotelRepository;
import com.example.booking_app_be.repository.RoomRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomService {
    RoomRepository roomRepository;
    HotelRepository hotelRepository;
    RoomMapper roomMapper;

    public List<RoomResponse> getAllByHotel(Long id){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> {
            throw new AppException(ErrorCode.HOTEL_NOT_EXISTED);
        });
        List<RoomResponse> roomServices = roomRepository.findAllByHotel(hotel).stream().map(room ->
                roomMapper.toRoomResponse(room)).toList();

        return roomServices;
    }
    public RoomResponse createRoom(RoomRequest request){
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(() -> {
            throw new AppException(ErrorCode.HOTEL_NOT_EXISTED);
        });

        Room room = roomMapper.toRoom(request);
        room.setHotel(hotel);

        roomRepository.save(room);

        RoomResponse roomResponse = roomMapper.toRoomResponse(room);
        roomResponse.setHotel(hotel);

        return roomResponse;
    }

    public RoomResponse updateRoom(Long id, RoomRequest request){

        Room room = roomRepository.findById(id).orElseThrow();

        roomMapper.updateRoom(room, request);

        RoomResponse roomResponse = roomMapper.toRoomResponse(roomRepository.save(room));

        return roomResponse;
    }

    public void deleteRoom(Long id){
        roomRepository.deleteById(id);
    }
}
