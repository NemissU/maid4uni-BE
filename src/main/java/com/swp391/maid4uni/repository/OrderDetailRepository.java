package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findAllByOrderIdAndLogicalDeleteStatus(int id, short logicalDeleteStatus);
}
