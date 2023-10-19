package com.swp391.maid4uni.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
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
    @Column(name = "service_name", nullable = false)
    String name;
    @Column
    String description;
    @Column(nullable = false)
    double price;
    @Column(name = "created_at", nullable = false)
    Date createdAt;
    @Column(name = "updated_at")
    Date updateAt;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    Account creator;

    @ManyToMany(mappedBy = "serviceList", fetch = FetchType.LAZY)
    List<Package> belongedPackage;

    @OneToMany(mappedBy = "service")
    List<Task> taskList;
}
