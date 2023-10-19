package com.swp391.maid4uni.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

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
    @Column(name = "method_name")
    String methodName;
    @Column(name = "payment_time")
    Date paymentTime;
    @Column(name = "payment_status")
    String paymentStatus;

    @OneToMany(mappedBy = "paymentMethod")
    List<Order> orders;
}
