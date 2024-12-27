package com.example.booking_app_be.dto.response;


import com.example.booking_app_be.constant.PaymentMethod;
import com.example.booking_app_be.constant.StatusOrder;
import com.example.booking_app_be.entity.BookedRoom;
import com.example.booking_app_be.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    Long id;

    LocalDate bookingDate;
    StatusOrder statusOrder;
    PaymentMethod paymentMethod;

    User user;
    BookedRoom bookedRoom;

    Date onCreate;
    Date onUpdate;
}
