package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Package repository.
 */
public interface PackageRepository extends JpaRepository<Package,Integer> {
    /**
     * Find all by logical delete status list.
     *
     * @param logicalDeleteStatus the logical delete status
     * @return the list
     */
    List<Package> findAllByLogicalDeleteStatus(int logicalDeleteStatus);

    Package findByIdAndLogicalDeleteStatus(int id, int logicalDeleteStatus);
}
