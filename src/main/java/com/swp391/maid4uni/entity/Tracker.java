package com.swp391.maid4uni.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The type Tracker.
 */
@Entity
@Table(name = "TRACKER_TBL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tracker {
    @Id
    @Column(name = "tracker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "recorded_time")
    Date recordedTime;
    @Column(name = "task_done")
    int taskDone;
    @OneToOne
    @JoinColumn(name = "staff_id")
    Account account;

    @ManyToMany
    @JoinTable(name = "tracker_task"
            , joinColumns = @JoinColumn(name = "tracker_id")
            , inverseJoinColumns = @JoinColumn(name = "task_id"))
    List<Task> taskList;

}
