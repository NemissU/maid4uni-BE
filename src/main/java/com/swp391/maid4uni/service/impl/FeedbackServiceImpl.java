package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.FeedbackConverter;
import com.swp391.maid4uni.dto.AccountDto;
import com.swp391.maid4uni.entity.Feedback;
import com.swp391.maid4uni.exception.Maid4UniException;
import com.swp391.maid4uni.repository.FeedbackRepository;
import com.swp391.maid4uni.response.FeedbackResponse;
import com.swp391.maid4uni.service.FeedbackService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Feedback service.
 */
@Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@NoArgsConstructor(force = true)
@Builder
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Override
    public List<FeedbackResponse> getAllFeedbackList() {
        List<Feedback> feedbackList = feedbackRepository.findAllByLogicalDeleteStatus(0);
        List<FeedbackResponse> feedbackResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(feedbackList)) {
            feedbackResponseList =
                    feedbackList.stream()
                            .map(FeedbackConverter.INSTANCE::fromFeedbackToFeedbackResponse)
                            .toList();
        }
        return feedbackResponseList;
    }

    @Override
    public List<FeedbackResponse> getFeedbackByReceiverId(AccountDto accountDto) {
        List<Feedback> feedbackList = feedbackRepository.findAllByReceiverIdAndLogicalDeleteStatus(accountDto.getId(),0);
        List<FeedbackResponse> feedbackResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(feedbackList)) {
            feedbackResponseList =
                    feedbackList.stream()
                            .map(FeedbackConverter.INSTANCE::fromFeedbackToFeedbackResponse)
                            .toList();
        }
        return feedbackResponseList;
    }

    @Override
    public List<FeedbackResponse> getFeedbackBySenderId(int id) {
        List<Feedback> feedbackList = feedbackRepository.findAllBySenderIdAndLogicalDeleteStatus(id,0);
        List<FeedbackResponse> feedbackResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(feedbackList)) {
            feedbackResponseList =
                    feedbackList.stream()
                            .map(FeedbackConverter.INSTANCE::fromFeedbackToFeedbackResponse)
                            .toList();
        } else
            throw Maid4UniException.notFound("SenderId does not exist");
        return feedbackResponseList;
    }
}
