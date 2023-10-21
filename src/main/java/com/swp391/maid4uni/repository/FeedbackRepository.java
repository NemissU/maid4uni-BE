package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Feedback repository.
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
