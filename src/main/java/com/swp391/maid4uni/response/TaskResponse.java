package com.swp391.maid4uni.response;

import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Service;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponse {
    int id;
    boolean status;
}
