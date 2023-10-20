package com.swp391.maid4uni.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * The type Login by username request.
 */
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class LoginByUsernameRequest {
    String username;
    String password;
}
