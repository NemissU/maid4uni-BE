package com.swp391.maid4uni.response;

import com.swp391.maid4uni.entity.Account;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PackageResponse {
    String name;
    String description;
    double price;
    Date createdAt;
    Date updatedAt;
    short logicalDeleteStatus;
    Account creator;
    List<ServiceResponse> serviceList;
}
