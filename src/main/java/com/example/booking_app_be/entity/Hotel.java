package com.example.booking_app_be.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String pathImage;
    String nameHotel;
    String address;
    String description;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    boolean active;
    Date onCreate;
    Date onUpdate;
}
