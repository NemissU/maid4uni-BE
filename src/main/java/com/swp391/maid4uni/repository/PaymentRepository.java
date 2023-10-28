package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
