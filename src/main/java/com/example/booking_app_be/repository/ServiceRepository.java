package com.example.booking_app_be.repository;

import com.example.booking_app_be.entity.Hotel;
import com.example.booking_app_be.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findAllByHotel(Hotel hotel);
}
