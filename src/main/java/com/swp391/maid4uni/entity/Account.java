package com.swp391.maid4uni.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name = "ACCOUNT_TBL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    int id;

    @Column(nullable = false, unique = true, name = "usename")
    String userName;
    @Column(nullable = false)
    String password;
    @Column(unique = true)
    String email;
    @Column(unique = true, name = "phone_number")
    String phoneNumber;
    @Column
    String fullName;
    @Column(name = "dob")
    Date dOB;
    @Column
    String gender;
    @Column
    String address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;


}
