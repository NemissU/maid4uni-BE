package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value = "SELECT t FROM Task t WHERE t.staff.id =:id ORDER BY t.orderDetail.workDay ASC")
    List<Task> findAllByStaffIdWithOffSetAndLimit(@Param("id")int id
            , Pageable pageable);
}
