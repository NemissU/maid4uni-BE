package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.FeedbackConverter;
import com.swp391.maid4uni.dto.FeedBackDto;
import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Feedback;
import com.swp391.maid4uni.entity.Rating;
import com.swp391.maid4uni.exception.Maid4UniException;
import com.swp391.maid4uni.repository.AccountRepository;
import com.swp391.maid4uni.repository.FeedbackRepository;
import com.swp391.maid4uni.repository.RatingRepository;
import com.swp391.maid4uni.response.BestFeedbackResponse;
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
@RequiredArgsConstructor
@Builder
public class FeedbackServiceImpl implements FeedbackService {
    FeedbackRepository feedbackRepository;
    AccountRepository accountRepository;
    RatingRepository ratingRepository;

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
    public List<FeedbackResponse> getFeedbackByReceiverId(int id) {
        List<Feedback> feedbackList = feedbackRepository.findAllByReceiverIdAndLogicalDeleteStatus(id, 0);
        List<FeedbackResponse> feedbackResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(feedbackList)) {
            feedbackResponseList =
                    feedbackList.stream()
                            .map(FeedbackConverter.INSTANCE::fromFeedbackToFeedbackResponse)
                            .toList();
        } else
            throw Maid4UniException.notFound("Not found any feedbacks of receiver: " + id);
        return feedbackResponseList;
    }

    @Override
    public List<FeedbackResponse> getFeedbackBySenderId(int id) {
        List<Feedback> feedbackList = feedbackRepository.findAllBySenderIdAndLogicalDeleteStatus(id, 0);
        List<FeedbackResponse> feedbackResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(feedbackList)) {
            feedbackResponseList =
                    feedbackList.stream()
                            .map(FeedbackConverter.INSTANCE::fromFeedbackToFeedbackResponse)
                            .toList();
        } else
            throw Maid4UniException.notFound("Not found any feedbacks of sender: " + id);
        return feedbackResponseList;
    }

    @Override
    public FeedbackResponse createFeedback(FeedBackDto feedBackDto) {
        Account sender = accountRepository.findAccountByIdAndLogicalDeleteStatus(feedBackDto.getSender().getId(), 0);
        Account receiver = accountRepository.findAccountByIdAndLogicalDeleteStatus(feedBackDto.getReceiver().getId(), 0);
        if (sender == null) {
            throw Maid4UniException.notFound("Sender does not exist");
        }
        if (receiver == null) {
            throw Maid4UniException.notFound("Receiver does not exist");
        }
        Feedback feedback = FeedbackConverter.INSTANCE.fromFeedbackDtoToFeedback(feedBackDto);
        feedbackRepository.save(feedback);
        return FeedbackConverter.INSTANCE.fromFeedbackToFeedbackResponse(feedback);
    }

    @Override
    public int getRatingByAccountId(int id) {
        Account staff = accountRepository.findAccountByIdAndLogicalDeleteStatus(id, 0);
        if (staff == null) {
            throw Maid4UniException.notFound("Staff does not exist");
        }
        int avgRating = 0;
//        List<FeedbackResponse> feedbackList = getFeedbackByReceiverId(id);
        List<Feedback> feedbackList = feedbackRepository.findAllByReceiverIdAndLogicalDeleteStatus(id, 0);
        if (!CollectionUtils.isEmpty(feedbackList)) {
            for (Feedback item : feedbackList) {
                avgRating += item.getRating().getStar();
            }
            avgRating = avgRating / feedbackList.size();
        } else {
            throw Maid4UniException.notFound("Staff doesn't have any feedback");
        }
        return avgRating;
    }

    @Override
    public List<BestFeedbackResponse> getBestReview() {
        // lấy ra 1 trong những feedback có highest star rating
        List<Rating> ratingList = ratingRepository.findTop5ByOrderByStarDesc();
        List<Feedback> feedbackList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(ratingList)) {
            for (Rating itemR: ratingList) {
                Feedback fb = feedbackRepository.findByRatingAndLogicalDeleteStatus(itemR, 0);
                feedbackList.add(fb);
            }
        }
        List<BestFeedbackResponse> bestFeedbackResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(feedbackList)) {
            for (Feedback itemF : feedbackList) {
                BestFeedbackResponse bfr = new BestFeedbackResponse();
                bfr.setStar(itemF.getRating().getStar());
                bfr.setFullname(itemF.getReceiver().getFullName());
                bfr.setContent(itemF.getComment());
                bestFeedbackResponseList.add(bfr);
            }
        }
        return bestFeedbackResponseList;
    }


}
