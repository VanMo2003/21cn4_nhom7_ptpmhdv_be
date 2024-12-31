package com.example.booking_app_be.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelRequest {
    String pathImage;
    String nameHotel;
    String address;
    String description;
    double price;
    int numberOfRoom;
    String userID;

    boolean active = true;
    Date onCreate = new Date();
    Date onUpdate = new Date();
}
