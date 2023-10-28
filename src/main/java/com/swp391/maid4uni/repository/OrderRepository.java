package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByCustomerIdAndLogicalDeleteStatus(int id, short logicalDeleteStatus);
}
