package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * The interface Service repository.
 */
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    /**
     * Find all by logical delete status list.
     *
     * @param loicalDeleteStatus the loical delete status
     * @return the list
     */
    List<Service> findAllByLogicalDeleteStatus(int loicalDeleteStatus);

    @Query(value = "SELECT s FROM Service s WHERE s.logicalDeleteStatus = :logicalDeleteStatus ORDER BY s.id")
    List<Service> findAllByLogicalDeleteStatusWithOffsetAndLimit(@Param("logicalDeleteStatus") int logicalDeleteStatus, Pageable pageable);
    /**
     * Find by id and logical delete status service.
     *
     * @param id                  the id
     * @param logicalDeleteStatus the logical delete status
     * @return the service
     */
    Service findByIdAndLogicalDeleteStatus(int id, int logicalDeleteStatus);


}
