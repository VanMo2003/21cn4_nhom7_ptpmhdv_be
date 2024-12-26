package com.example.booking_app_be.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String fullName;
    String phone;
    String gender;
    LocalDate dateOfBirth;
    String birthPlace;
    String address;
    RoleResponse role;
    boolean active;
    Date onCreate;
    Date onUpdate;
}
// da fix