package com.example.booking_app_be.service;

import com.example.booking_app_be.dto.request.BookingRequest;
import com.example.booking_app_be.dto.response.BookingResponse;
import com.example.booking_app_be.entity.BookedRoom;
import com.example.booking_app_be.entity.Booking;
import com.example.booking_app_be.entity.User;
import com.example.booking_app_be.exception.AppException;
import com.example.booking_app_be.exception.ErrorCode;
import com.example.booking_app_be.mapper.BookingMapper;
import com.example.booking_app_be.repository.BookingRepository;
import com.example.booking_app_be.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {
    BookingRepository bookingRepository;
    UserRepository userRepository;
    BookingMapper bookingMapper;

    public void createBookingService(BookingRequest request, BookedRoom bookedRoom){
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    public List<BookingResponse> getBookingByUser(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        List<BookingResponse> bookings = bookingRepository.findByUser(user).stream().map(booking -> bookingMapper.toBookingResponse(booking)).toList();

        return bookings;
    }

    public void createBookingService(BookingRequest request, BookedRoom bookedRoom){
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Booking booking = bookingMapper.toBooking(request);
        booking.setUser(user);
        booking.setBookedRoom(bookedRoom);


        bookingRepository.save(booking);
    }
}
