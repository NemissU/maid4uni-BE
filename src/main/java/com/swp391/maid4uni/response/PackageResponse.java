package com.swp391.maid4uni.response;

import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.enums.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

/**
 * The type Package response.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PackageResponse {
    int id;
    String name;
    String description;
    double price;
    Date createdAt;
    String imageUrl;
    Date updatedAt;
    AccountResponse creator;
    Category category;
    List<ServiceResponse> serviceList;
}
