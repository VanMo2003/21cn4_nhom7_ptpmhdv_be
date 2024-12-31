package com.example.booking_app_be.service;

import com.example.booking_app_be.dto.response.StatisticalResponse;
import com.example.booking_app_be.entity.Booking;
import com.example.booking_app_be.entity.Hotel;
import com.example.booking_app_be.entity.Statistical;
import com.example.booking_app_be.entity.User;
import com.example.booking_app_be.mapper.HotelMapper;
import com.example.booking_app_be.mapper.StatisticalMapper;
import com.example.booking_app_be.repository.BookingRepository;
import com.example.booking_app_be.repository.HotelRepository;
import com.example.booking_app_be.repository.StatisticalRepository;
import com.example.booking_app_be.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticalService {
    StatisticalRepository statisticalRepository;
    StatisticalMapper statisticalMapper;
    HotelMapper hotelMapper;
    HotelRepository hotelRepository;
    UserRepository userRepository;
    BookingRepository bookingRepository;

    public List<StatisticalResponse> getAllByHotelByYear(){
        Hotel hotel = getMyHotel();

        List<StatisticalResponse> statisticalResponses =  new ArrayList<>();;
        statisticalRepository.findByHotel(hotel).stream().map(statistical -> statisticalResponses.add(statisticalMapper.toStatisticalResponse(statistical))).toList();

        Collections.sort(statisticalResponses, Comparator.comparing(StatisticalResponse::getRevenueMonth));


        return statisticalResponses;
    }

    @PreAuthorize("hasRole('HOTELIER')")
    public StatisticalResponse updateMonthlyStatistic(StatisticalRequest request){
        LocalDate firstMonth = LocalDate.parse(request.getRevenueMonth().format(DateTimeFormatter.ofPattern("yyyy-MM")) + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Hotel hotel = getMyHotel();

        Optional<Statistical> statistical = statisticalRepository.findByHotelAndRevenueMonth(
                hotel.getId(),
                firstMonth);

        if (statistical.isPresent()){
            statistical.get().setTotalRevenue(monthlyRevenue(hotel.getId()));
        }else{
            statistical = Optional.of(statisticalMapper.toStatistical(request));
            statistical.get().setRevenueMonth(firstMonth);
            statistical.get().setHotel(hotel);
        }


        statisticalRepository.save(statistical.get());

        StatisticalResponse statisticalResponse = statisticalMapper.toStatisticalResponse(statistical.get());
        statisticalResponse.setHotel(hotelMapper.toHotelResponse(hotel));

        return statisticalResponse;
    }

    private Hotel getMyHotel(){
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Hotel hotel = hotelRepository.findByUserId(user.getId()).orElseThrow();
        return hotel;
    }

    private double monthlyRevenue(Long hotelId){
        List<Booking> bookings = bookingRepository.getAllBookingMonthByHotel(hotelId, LocalDate.now());

        double revenue = bookings.stream().mapToDouble(Booking::getPrice).sum();

        return  revenue;
    }
}