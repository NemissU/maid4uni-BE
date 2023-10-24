package com.swp391.maid4uni.service;

import com.swp391.maid4uni.dto.AccountDto;
import com.swp391.maid4uni.response.FeedbackResponse;

import java.util.List;

/**
 * The interface Feedback service.
 */
public interface FeedbackService {
    /**
     * Gets all feedback list.
     *
     * @return the all feedback list
     */
    List<FeedbackResponse> getAllFeedbackList();

    /**
     * Gets feedback by receiver id.
     *
     * @param id the id
     * @return the feedback by receiver id
     */
    List<FeedbackResponse> getFeedbackByReceiverId(int id);

    /**
     * Gets feedback by sender id.
     *
     * @param id the id
     * @return the feedback by sender id
     */
    List<FeedbackResponse> getFeedbackBySenderId(int id);
}
