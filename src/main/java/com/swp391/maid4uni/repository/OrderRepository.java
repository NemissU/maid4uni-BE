package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByCustomerIdAndLogicalDeleteStatus(int id, short logicalDeleteStatus);

    Order findByIdAndLogicalDeleteStatus(int id, int logicalDeleteStatus);

    @Query(value = "SELECT o FROM Order o WHERE o.logicalDeleteStatus =:logicalDeleteStatus ORDER BY o.createdAt DESC")
    List<Order> findAllOrderByCreatedAtDescWithOffSetAndLimit(@Param("logicalDeleteStatus")int logicalDeleteStatus, Pageable pageable);

    List<Order> findByLogicalDeleteStatusAndCreatedAtBetween(int logicalDeleteStatus, LocalDateTime startDate, LocalDateTime endDate);
}
