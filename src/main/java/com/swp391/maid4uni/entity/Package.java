package com.swp391.maid4uni.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PACKAGE_TBL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    int id;

    @Column(name="num_staff")
    private int numStaff;

    @Column(name = "package_name")
    String packageName;

    @Column
    String description;

    @Column
    double price;

    @Column(name = "create_time")
    Date createdAt;

    @Column(name = "update_time")
    Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "provided_by", referencedColumnName = "account_id")
    Account accountId;

    @ManyToMany
    @JoinTable(
            name="PACKAGE_SERVICE",
            joinColumns=@JoinColumn(name="package_id"),
            inverseJoinColumns=@JoinColumn(name="service_id")
    )
    private List<Service> services;
}
