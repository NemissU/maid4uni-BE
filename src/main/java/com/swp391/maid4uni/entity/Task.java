package com.swp391.maid4uni.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "task_staff"
            ,joinColumns = @JoinColumn(name = "task_id")
            ,inverseJoinColumns = @JoinColumn(name = "staff_id"))
    List<Account> staffs;

    @ManyToOne
    @JoinColumn(name = "order_detail_id")
    OrderDetail orderDetail;

    @ManyToMany(mappedBy = "taskList",fetch = FetchType.LAZY)
    List<Tracker> belongedTrackers;
}
