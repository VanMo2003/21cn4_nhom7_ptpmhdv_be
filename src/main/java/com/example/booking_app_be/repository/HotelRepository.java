package com.example.booking_app_be.repository;

import com.example.booking_app_be.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface HotelRepository extends JpaRepository<Hotel, Long>{
    boolean existsByUserId(String userId);
    Optional<Hotel> findByUserId(String userId);
}
