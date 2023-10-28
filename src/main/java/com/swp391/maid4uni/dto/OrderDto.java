package com.swp391.maid4uni.dto;

import com.swp391.maid4uni.entity.Payment;
import com.swp391.maid4uni.enums.PeriodType;
import com.swp391.maid4uni.request.AccountDutyRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
    LocalDateTime time;
    int duration;
    PeriodType periodType;
    AccountDto customer;
    Payment paymentMethod;
}
