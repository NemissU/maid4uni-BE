package com.swp391.maid4uni.dto;

import com.swp391.maid4uni.entity.OrderDetail;
import com.swp391.maid4uni.entity.Tracker;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDto {
    int id;
    boolean status;
    ServiceDto service;
    List<AccountDto> staffs;
    OrderDetailDto orderDetail;
    List<TrackerDto> belongedTrackers;
}
