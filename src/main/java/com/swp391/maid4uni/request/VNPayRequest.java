package com.swp391.maid4uni.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class VNPayRequest {
    int orderTotal;
    String orderInfo;
}
