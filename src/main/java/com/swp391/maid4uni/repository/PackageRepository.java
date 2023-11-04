package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Package;
import com.swp391.maid4uni.enums.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT p FROM Package p WHERE p.category = :category AND p.logicalDeleteStatus = :logicalDeleteStatus ORDER BY p.id")
    List<Package> findByCategoryAndLogicalDeleteStatusWithOffsetAndLimit(@Param("category") Category category, @Param("logicalDeleteStatus") int logicalDeleteStatus, Pageable pageable);
}
