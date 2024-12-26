package com.example.booking_app_be.dto.request;

import com.example.booking_app_be.constant.PaymentMethod;
import com.example.booking_app_be.constant.StatusOrder;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    LocalDate bookingDate = LocalDate.now();

    StatusOrder statusOrder = StatusOrder.PENDING;
    PaymentMethod paymentMethod;

    String userId;

    Long bookedRoomId;

    Date onCreate = new Date();
    Date onUpdate = new Date();
}