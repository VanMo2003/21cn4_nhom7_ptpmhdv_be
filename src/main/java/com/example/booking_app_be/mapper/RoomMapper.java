package com.example.booking_app_be.mapper;


import com.example.booking_app_be.dto.request.RoomRequest;
import com.example.booking_app_be.dto.response.RoomResponse;
import com.example.booking_app_be.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "hotel", ignore = true)
    Room toRoom(RoomRequest request);
    @Mapping(target = "hotel", ignore = true)
    RoomResponse toRoomResponse(Room user);
    @Mapping(target = "hotel", ignore = true)
    void updateRoom(@MappingTarget Room user, RoomRequest request);
}
