package com.example.booking_app_be.dto.response;


import com.example.booking_app_be.entity.Hotel;
import com.example.booking_app_be.entity.Room;
import com.example.booking_app_be.entity.Service;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookedRoomResponse {
    Long id;
    LocalDate arrivalDate;
    LocalDate departureDate;
    List<RoomResponse> rooms;
    List<ServiceResponse> services;
    double price;
    boolean isCheckedIn = false;
    boolean isCheckedOut = false;
    String note;
    Hotel hotel;
    Date onCreate;
    Date onUpdate;
}
