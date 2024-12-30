package com.example.booking_app_be.mapper;

import com.example.booking_app_be.dto.request.BookRequest;
import com.example.booking_app_be.dto.request.BookedRoomRequest;
import com.example.booking_app_be.dto.response.BookedRoomResponse;
import com.example.booking_app_be.entity.BookedRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookedRoomMapper {
    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "services", ignore = true)
    BookedRoom toBookedRoom(BookedRoomRequest request);

    BookedRoomRequest toBookedRoomRequest(BookRequest request);

    BookedRoomResponse toBookedRoomResponse(BookedRoom bookedRoom);
    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "services", ignore = true)
    void updateBookedRoom(@MappingTarget BookedRoom bookedRoom, BookedRoomRequest request);
}