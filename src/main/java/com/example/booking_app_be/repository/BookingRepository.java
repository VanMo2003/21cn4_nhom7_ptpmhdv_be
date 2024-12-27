package com.example.booking_app_be.repository;


import com.example.booking_app_be.entity.Booking;
import com.example.booking_app_be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
