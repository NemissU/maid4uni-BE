package com.swp391.maid4uni.request;

import com.swp391.maid4uni.entity.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterAccountRequest {
    int id;
    String username;
    String password;
    String email;
    String phoneNumber;
    String fullName;
    Date dOB;
    String gender;
    String address;
    String img;
}
