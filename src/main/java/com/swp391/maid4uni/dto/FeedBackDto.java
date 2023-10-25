package com.swp391.maid4uni.dto;

import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Rating;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedBackDto {
    int id;
    String comment;
    LocalDate date;
    AccountDto sender;
    AccountDto receiver;
    RatingDto rating;
}
