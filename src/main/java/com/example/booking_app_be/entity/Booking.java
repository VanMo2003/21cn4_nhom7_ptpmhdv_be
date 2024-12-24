package com.example.booking_app_be.entity;

import com.example.booking_app_be.constant.PaymentMethod;
import com.example.booking_app_be.constant.StatusOrder;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDate bookingDate;

    @Enumerated(EnumType.STRING)
    StatusOrder statusOrder;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @OneToOne
    BookedRoom bookedRoom;

    Date onCreate;
    Date onUpdate;
}
