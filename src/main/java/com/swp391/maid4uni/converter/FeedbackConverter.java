package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.AccountDto;
import com.swp391.maid4uni.dto.FeedBackDto;
import com.swp391.maid4uni.dto.RatingDto;
import com.swp391.maid4uni.entity.Feedback;
import com.swp391.maid4uni.request.AccountDutyRequest;
import com.swp391.maid4uni.request.CreateFeedbackRequest;
import com.swp391.maid4uni.request.RatingRequest;
import com.swp391.maid4uni.response.FeedbackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The interface Feedback converter.
 */
@Mapper(componentModel = "spring")
public interface FeedbackConverter {
    /**
     * The constant INSTANCE.
     */
    FeedbackConverter INSTANCE = Mappers.getMapper(FeedbackConverter.class);

    FeedBackDto fromCreateFeedbackRequestToFeedbackDto(CreateFeedbackRequest createFeedbackRequest);

    /**
     * From feedback to feedback response feedback response.
     *
     * @param feedback the feedback
     * @return the feedback response
     */
    FeedbackResponse fromFeedbackToFeedbackResponse(Feedback feedback);

    Feedback fromFeedbackDtoToFeedback(FeedBackDto feedBackDto);

    AccountDto mapAccountDutyRequest(AccountDutyRequest accountDutyRequest);

    RatingDto mapRatingRequest(RatingRequest ratingRequest);
}
