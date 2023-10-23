package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package,Integer> {
    List<Package> findAllByLogicalDeleteStatus(int logicalDeleteStatus);
}
