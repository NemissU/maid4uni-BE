package com.swp391.maid4uni.response;

import com.swp391.maid4uni.entity.Account;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * The type Service response.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceResponse {
    String name;
    String description;
    double price;
    Date createdAt;
    Date updateAt;
    AccountResponse creator;
}
