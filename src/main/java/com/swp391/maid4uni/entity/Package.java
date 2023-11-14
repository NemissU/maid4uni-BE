package com.swp391.maid4uni.entity;

import com.swp391.maid4uni.enums.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;
import java.util.List;

/**
 * The type Package.
 */
@Entity
@EnableJpaAuditing
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
    @Column(name = "package_name", nullable = false)
    String name;
    @Column
    String description;
    @Column(nullable = false)
    double price;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    Date updatedAt;
    @Column
    short logicalDeleteStatus;
    @Enumerated
    Category category;
    @Column
    String imageUrl;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    Account creator;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "package_service"
            , joinColumns = @JoinColumn(name = "package_id")
            , inverseJoinColumns = @JoinColumn(name = "service_id"))
    List<Service> serviceList;

    @OneToMany(mappedBy = "aPackage")
    List<Order> orderList;
}
