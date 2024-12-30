package com.example.booking_app_be.mapper;



import com.example.booking_app_be.dto.request.HotelRequest;
import com.example.booking_app_be.dto.response.HotelResponse;
import com.example.booking_app_be.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;



@Mapper(componentModel = "spring")
public interface HotelMapper {
    @Mapping(target = "user", ignore = true)
    Hotel toHotel(HotelRequest request);

    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "services", ignore = true)
    HotelResponse toHotelResponse(Hotel hotel);

    @Mapping(target = "user", ignore = true)
    void updateHotel(@MappingTarget Hotel hotel, HotelRequest request);
}
