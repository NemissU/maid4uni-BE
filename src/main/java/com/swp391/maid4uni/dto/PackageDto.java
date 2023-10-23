package com.swp391.maid4uni.dto;

import com.swp391.maid4uni.response.AccountResponse;
import com.swp391.maid4uni.response.ServiceResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PackageDto {
    int id;
    String name;
    String description;
    double price;
    Date createdAt;
    Date updatedAt;
    short logicalDeleteStatus;
    AccountDto creator;
    List<ServiceDto> serviceList;
}
