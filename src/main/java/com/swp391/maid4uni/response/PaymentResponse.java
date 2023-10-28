package com.swp391.maid4uni.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    String paymentContent;
    LocalDateTime paymentTime;
    String paymentStatus;
}
