package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Feedback repository.
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findAllByReceiverIdAndLogicalDeleteStatus(int receiverId, int logicalDeleteStatus);

    List<Feedback> findAllBySenderIdAndLogicalDeleteStatus(int senderId, int logicalDeleteStatus);

    List<Feedback> findAllByLogicalDeleteStatus(int logicalDeleteStatus);
}
