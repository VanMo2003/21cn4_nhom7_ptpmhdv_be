package com.example.booking_app_be.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelResponse {
    Long id;
    String pathImage;
    String nameHotel;
    String address;
    String description;
    double price;
    int numberOfRoom;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    UserResponse user;

    List<RoomResponse> rooms;

    List<ServiceResponse> services;

    boolean active;
    Date onCreate;
    Date onUpdate;
}
