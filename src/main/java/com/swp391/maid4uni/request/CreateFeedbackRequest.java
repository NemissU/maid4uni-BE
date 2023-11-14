package com.swp391.maid4uni.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateFeedbackRequest {
    int id;
    String comment;
    AccountDutyRequest sender;
    AccountDutyRequest receiver;
    RatingRequest rating;
}
