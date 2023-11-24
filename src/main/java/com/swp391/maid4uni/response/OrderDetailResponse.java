package com.swp391.maid4uni.response;

import com.swp391.maid4uni.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    int id;
    LocalDate workDay;
    LocalTime startTime;
    LocalTime endTime;
    Status status;
    OrderResponse order;
    List<TaskDutyResponse> taskList;
}
