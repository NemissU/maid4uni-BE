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

    List<FeedbackResponse> getFeedbackByReceiverId(AccountDto accountDto);

    List<FeedbackResponse> getFeedbackBySenderId(AccountDto accountDto);
}
