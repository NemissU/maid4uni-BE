package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
