package com.swp391.maid4uni.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * The type Order detail.
 */
@Entity
@Table(name = "ORDER_DETAIL_TBL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetail {
    @Id
    @Column(name = "order_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    LocalDate workDay;
    @Column(name = "start_time")
    Time startTime;
    @Column(name = "end_time")
    Time endTime;
    @Column
    boolean status;
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
    @OneToMany(mappedBy = "orderDetail")
    List<Task> taskList;
    @Column
    short logicalDeleteStatus;
}
