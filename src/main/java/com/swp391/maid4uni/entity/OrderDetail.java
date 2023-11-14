package com.swp391.maid4uni.entity;

import com.swp391.maid4uni.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
    LocalDate workDay; // format YY/MM/DD
    @Column(name = "start_time")
    LocalTime startTime;
    @Column(name = "end_time")
    LocalTime endTime;
    @Enumerated
    Status status;
    @Column
    short logicalDeleteStatus;

    /* TABLE REFERENCE */
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
    @OneToMany(mappedBy = "orderDetail")
    List<Task> taskList;

}
