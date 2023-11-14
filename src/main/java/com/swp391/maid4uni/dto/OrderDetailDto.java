package com.swp391.maid4uni.dto;

import com.swp391.maid4uni.entity.Order;
import com.swp391.maid4uni.entity.Task;
import com.swp391.maid4uni.enums.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailDto {
    int id;
    LocalDate workDay;
    LocalTime startTime;
    LocalTime endTime;
    Status status;
    OrderDto order;
    List<TaskDto> taskList;
}
