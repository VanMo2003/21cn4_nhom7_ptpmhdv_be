package com.example.booking_app_be.mapper;


import com.example.booking_app_be.dto.request.BookRequest;
import com.example.booking_app_be.dto.request.BookingRequest;
import com.example.booking_app_be.dto.response.BookingResponse;
import com.example.booking_app_be.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "bookedRoom", ignore = true)
    Booking toBooking(BookingRequest request);


    BookingRequest toBookingRequest(BookRequest request);

    BookingResponse toBookingResponse(Booking booking);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "bookedRoom", ignore = true)
    void updateBooking(@MappingTarget Booking booking, BookingRequest request);
}
