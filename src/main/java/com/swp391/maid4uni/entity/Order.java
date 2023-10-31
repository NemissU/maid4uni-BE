package com.swp391.maid4uni.entity;


import com.swp391.maid4uni.enums.PeriodType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Order.
 */
@Entity
@Table(name = "ORDER_TBL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    double price;
    @Column
    String address;
    @Column
    LocalDate startDay; // format YY/MM/DD
    @Column
    LocalDate endDay; // format YY/MM/DD
    @Column
    LocalTime startTime;
    @Column
    LocalDateTime createdAt;
    @Column
    int duration;
    @Column(name = "period_Type")
    PeriodType periodType;
    @Column
    short logicalDeleteStatus;
    @ManyToOne
    @JoinColumn(name = "cutomer_id")
    Account customer;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetailList;

    @OneToOne
    @JoinColumn(name = "payment_id")
    Payment payment;

    @ManyToOne
    @JoinColumn(name = "package_id")
    Package aPackage;

}
