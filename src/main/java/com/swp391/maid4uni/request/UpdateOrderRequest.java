package com.swp391.maid4uni.request;

import com.swp391.maid4uni.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UpdateOrderRequest {
    int id;
    OrderStatus status;
}
