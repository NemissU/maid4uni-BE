package com.swp391.maid4uni.response;

import com.swp391.maid4uni.entity.Rating;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

/**
 * The type Feedback response.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedbackResponse {
    /**
     * The Comment.
     */
    String comment;
    /**
     * The Rating.
     */
    RatingResponse rating;
    LocalDate date;
}
