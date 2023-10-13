package com.swp391.maid4uni.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SERVICE_TBL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    int id;

    @Column(name = "service_name")
    String serviceName;

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

    @ManyToMany(mappedBy="services")
    private List<Package> packages;
}
