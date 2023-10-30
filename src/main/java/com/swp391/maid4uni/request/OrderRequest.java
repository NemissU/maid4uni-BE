package com.swp391.maid4uni.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.OrderDetail;
import com.swp391.maid4uni.entity.Payment;
import com.swp391.maid4uni.enums.PeriodType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class OrderRequest {
    double price;
    String address;
    int duration;
    ArrayList<Integer> workDay;
    // todo startTime
    LocalTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    LocalDate startDay; // format YY/MM/DD
    PeriodType periodType;
    AccountDutyRequest customer;
    PackageDutyRequest pkgDuty;
}
