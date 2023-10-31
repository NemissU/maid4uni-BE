package com.swp391.maid4uni.dto;

import com.swp391.maid4uni.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * The type Account dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AccountDto {
    int id;
    String username;
    String email;
    String phoneNumber;
    String fullName;
    Date dOB;
    String gender;
    String address;
    String img;
    Role role;

}
