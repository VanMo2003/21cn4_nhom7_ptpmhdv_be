package com.example.booking_app_be.repository;


import com.example.booking_app_be.entity.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long> {

    @Query(nativeQuery = true, value = "SELECT * \n" +
            "FROM booking_app.booked_room\n" +
            "where (booking_app.booked_room.arrival_date between :arrivalDate and :departureDate\n" +
            "or booking_app.booked_room.departure_date between :arrivalDate and  :departureDate)\n" +
            "and booking_app.booked_room.hotel_id = :hotel")
    List<BookedRoom> getAllBookedRoomIntersectArrivalDateAndDepartureDate(
                    @Param("arrivalDate") LocalDate arrivalDate,
                    @Param("departureDate") LocalDate departureDate,
                    @Param("hotel") Long hotelId);

}
