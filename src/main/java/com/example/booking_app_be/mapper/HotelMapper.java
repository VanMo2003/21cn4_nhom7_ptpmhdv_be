package com.example.booking_app_be.mapper;

import com.example.booking_app_be.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;



@Mapper(componentModel = "spring")
public interface HotelMapper<HotelRequest, HotelResponse> {
    @Mapping(target = "user", ignore = true)
    Hotel toHotel(HotelRequest request);

    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "services", ignore = true)
    HotelResponse toHotelResponse(Hotel hotel);

    @Mapping(target = "user", ignore = true)
    void updateHotel(@MappingTarget Hotel hotel, HotelRequest request);
}
