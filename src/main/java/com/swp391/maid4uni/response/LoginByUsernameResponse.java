package com.swp391.maid4uni.response;

import com.swp391.maid4uni.dto.AccountDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * The type Login by username response.
 */
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginByUsernameResponse {
    String accessToken;
    AccountDto account;
}
