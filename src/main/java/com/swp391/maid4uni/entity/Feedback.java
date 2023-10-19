package com.swp391.maid4uni.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.time.LocalDate;

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
    @Column(name = "content")
    String content;
    @Column(name = "date")
    LocalDate date;

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
