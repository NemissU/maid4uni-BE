package com.swp391.maid4uni.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDto {
    String paymentContent;
    LocalDateTime paymentTime;
    String paymentStatus;
}
