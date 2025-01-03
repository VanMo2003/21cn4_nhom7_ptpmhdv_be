package com.example.booking_app_be.dto.response;

import com.example.booking_app_be.entity.Hotel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceResponse {
    Long id;
    String name;
    double price;
    String description;
    HotelResponse hotel;
}
