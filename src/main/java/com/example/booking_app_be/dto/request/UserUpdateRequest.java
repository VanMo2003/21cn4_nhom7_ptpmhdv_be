package com.example.booking_app_be.dto.request;


import java.time.LocalDate;
import java.util.Date;

import com.example.booking_app_be.validator.DobConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String fullName;
    String phone;
    String gender;

    @DobConstraint(message = "INVALID_DOB")
    LocalDate dateOfBirth;

    String birthPlace;
    String address;
    boolean active = true;
    Date onUpdate = new Date();
}