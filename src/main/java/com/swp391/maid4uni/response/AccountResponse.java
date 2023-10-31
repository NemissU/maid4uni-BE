package com.swp391.maid4uni.response;

import com.swp391.maid4uni.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * The type Account response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
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
