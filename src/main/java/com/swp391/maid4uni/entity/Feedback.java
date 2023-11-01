package com.swp391.maid4uni.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

/**
 * The type Feedback.
 */
@Entity
@Table(name = "FEEDBACK_TBL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", nullable = false)
    int id;
    @Column(name = "comment")
    String comment;
    @CreationTimestamp
    @Column(name = "date")
    LocalDate date;
    @Column
    short logicalDeleteStatus;
    @ManyToOne
    @JoinColumn(name = "sender")
    Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver")
    Account receiver;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rating_id")
    Rating rating;
}
