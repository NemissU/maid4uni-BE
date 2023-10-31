package com.swp391.maid4uni.dto;

import com.swp391.maid4uni.entity.Payment;
import com.swp391.maid4uni.enums.PeriodType;
import com.swp391.maid4uni.request.AccountDutyRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    double price;
    String address;
    ArrayList<Integer> workDay;
    LocalDate startDay; // format YY/MM/DD
    LocalDate endDay; // format YY/MM/DD
    LocalDateTime createdAt;
    LocalTime startTime;
    int duration;
    PeriodType periodType;
    AccountDto customer;
    PaymentDto payment;
    PackageDto packageDto;
}
