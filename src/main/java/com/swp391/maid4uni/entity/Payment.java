package com.swp391.maid4uni.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * The type Payment.
 */
@Entity
@Table(name = "PAYMENT_TBL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "payment_content")
    String paymentContent;
    @Column(name = "payment_time")
    LocalDateTime paymentTime;
    @Column(name = "payment_status")
    String paymentStatus;
    @OneToMany(mappedBy = "payment")
    List<Order> orders;
}
