package com.swp391.maid4uni.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * The type Tracker.
 */
@Entity
@Table(name = "TRACKER_TBL")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tracker {
    @Id
    @Column(name = "tracker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @UpdateTimestamp
    @Column(name = "recorded_time")
    LocalDate recordedTime;
    @Column(name = "task_done")
    int taskDone;

    @OneToOne(mappedBy = "tracker")
    Account account;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tracker_task"
            , joinColumns = @JoinColumn(name = "tracker_id")
            , inverseJoinColumns = @JoinColumn(name = "task_id"))
    List<Task> taskList;

}
