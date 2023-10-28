package com.swp391.maid4uni.request;

import com.swp391.maid4uni.entity.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * The type Register account request.
 */
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterAccountRequest {
    String username;
    String password;
    String fullName;
    String phoneNumber;
    String email;
}
