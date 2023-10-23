package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findAllByLogicalDeleteStatus(int loicalDeleteStatus);

    Service findByIdAndLogicalDeleteStatus(int id, int logicalDeleteStatus);
}
