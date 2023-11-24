package com.swp391.maid4uni.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDutyResponse {
    int id;
    ServiceDutyResponse service;
    AccountDutyResponse staff;
}
