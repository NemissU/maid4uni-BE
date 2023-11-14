package com.swp391.maid4uni.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UpdateTaskRequest {
    int id;
    boolean status;
}
