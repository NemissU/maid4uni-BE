package com.swp391.maid4uni.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceDto {
    int id;
    String name;
    String description;
    double price;
    Date createdAt;
    Date updatedAt;
    short logicalDeleteStatus;
    AccountDto creator;
}
