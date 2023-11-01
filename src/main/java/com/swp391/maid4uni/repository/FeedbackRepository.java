package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Feedback repository.
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    /**
     * Find all by receiver id and logical delete status list.
     *
     * @param receiverId          the receiver id
     * @param logicalDeleteStatus the logical delete status
     * @return the list
     */
    List<Feedback> findAllByReceiverIdAndLogicalDeleteStatus(int receiverId, int logicalDeleteStatus);

    /**
     * Find all by sender id and logical delete status list.
     *
     * @param senderId            the sender id
     * @param logicalDeleteStatus the logical delete status
     * @return the list
     */
    List<Feedback> findAllBySenderIdAndLogicalDeleteStatus(int senderId, int logicalDeleteStatus);

    /**
     * Find all by logical delete status list.
     *
     * @param logicalDeleteStatus the logical delete status
     * @return the list
     */
    List<Feedback> findAllByLogicalDeleteStatus(int logicalDeleteStatus);

    Feedback findByRatingIdAndLogicalDeleteStatus(int id, int logicalDeleteStatus);
}
