package com.swp391.maid4uni.dto;

import com.swp391.maid4uni.entity.Order;
import com.swp391.maid4uni.entity.Task;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailDto {
    int id;
    LocalDate workDay;
    Time startTime;
    Time endTime;
    boolean status;
    OrderDto order;
    List<TaskDto> taskList;
}
