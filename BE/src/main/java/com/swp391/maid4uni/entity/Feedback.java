package com.swp391.maid4uni.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @Column(name="sender_username")
     String senderUsername;

    @ManyToOne
    @JoinColumn(name="sender_username", referencedColumnName="username")
     Account sender;

    @Column(name="receiver_username")
     String receiverUsername;

    @ManyToOne
    @JoinColumn(name="receiver_username", referencedColumnName="username")
     Account receiver;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="rating_id")
     Rating rating;
}
