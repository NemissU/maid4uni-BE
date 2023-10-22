package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Feedback repository.
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findAllByReceiverId(int receiverId);

    List<Feedback> findAllBySenderId(int senderId);
}
