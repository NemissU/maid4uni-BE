package com.swp391.maid4uni.dto;

import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Task;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackerDto {
    int id;
    Date recordedTime;
    int taskDone;
    AccountDto account;
    List<TaskDto> taskList;
}
