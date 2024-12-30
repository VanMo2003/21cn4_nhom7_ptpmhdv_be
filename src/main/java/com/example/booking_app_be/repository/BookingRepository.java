package com.example.booking_app.repository;

import com.example.booking_app.entity.Booking;
import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    @Query(nativeQuery = true, value = """
<<<<<<< HEAD
=======
        SELECT booking_app.booking.*\s
        FROM booking_app.booking
        LEFT JOIN booking_app.booked_room on booking_app.booked_room.id = booking_app.booking.booked_room_id
        WHERE booking_app.booked_room.hotel_id = :hotel;
    """)
    List<Booking> findByHotel(@Param("hotel") Long hotelId);


    @Query(nativeQuery = true, value = """
>>>>>>> main
    SELECT booking_app.booking.*\s
    FROM booking_app.booking
    LEFT JOIN booking_app.booked_room on booking_app.booked_room.id = booking_app.booking.booked_room_id
    where booking_app.booked_room.hotel_id = :hotel
    and booking_app.booking.status_order = "COMPLETED"
    and MONTH(booking_app.booked_room.arrival_date) = MONTH(:date) AND YEAR(booking_app.booked_room.arrival_date) = YEAR(:date);
    """)
<<<<<<< HEAD
    List<Booking> getAllBookingMonthByHotel(@Param("hotel") Long hotelId, @Param("date") LocalDate date);

=======
    List<Booking> getAllBookingMonthByHotel(@Param("hotel") Long hotelId,@Param("date") LocalDate date);
>>>>>>> main
}
