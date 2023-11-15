package com.swp391.maid4uni.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swp391.maid4uni.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

/**
 * The type Update account request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateAccountRequest {
    int id;
    String username;
    String password;
    String email;
    String phoneNumber;
    String fullName;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    LocalDate dOB;
    String gender;
    String address;
    String img;
    Role role;
}
