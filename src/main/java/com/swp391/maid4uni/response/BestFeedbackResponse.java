package com.swp391.maid4uni.response;

import com.swp391.maid4uni.entity.Account;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BestFeedbackResponse {
    String content;
    Integer star;
    String fullname;
}
