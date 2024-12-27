package com.example.booking_app_be.repository;

import com.example.booking_app_be.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface HotelRepository extends JpaRepository<Hotel, Long>{
}
