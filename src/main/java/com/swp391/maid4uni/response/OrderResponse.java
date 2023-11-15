package com.swp391.maid4uni.response;

import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.OrderDetail;
import com.swp391.maid4uni.entity.Payment;
import com.swp391.maid4uni.enums.OrderStatus;
import com.swp391.maid4uni.enums.PeriodType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class OrderResponse {
    int id;
    double price;
    String address;
    LocalDateTime createdAt;
    int duration;
    PeriodType periodType;
    ArrayList<Integer> workDay;
    LocalDate startDay; // format YY/MM/DD
    OrderStatus orderStatus;
    LocalDate endDay; // format YY/MM/DD
    PackageResponse pkgRes;
    PaymentResponse payment;
//    List<OrderDetailResponse> orderDetailResponseList;
//    PaymentResponse paymentMethod;
}
