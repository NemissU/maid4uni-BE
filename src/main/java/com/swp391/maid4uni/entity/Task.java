package com.swp391.maid4uni.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.util.List;

/**
 * The type Task.
 */
@Entity
@Table(name = "TASK_TBL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    boolean status;

    @ManyToOne
    @JoinColumn(name = "service_id")
    Service service;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    Account staff;

    @ManyToOne
    @JoinColumn(name = "order_detail_id")
    OrderDetail orderDetail;
}
