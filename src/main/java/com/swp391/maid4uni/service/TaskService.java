package com.swp391.maid4uni.service;

import com.swp391.maid4uni.dto.TaskDto;
import com.swp391.maid4uni.response.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse updateTask(TaskDto taskDto, int id);

    List<TaskResponse> getTaskByStaffId(int id, int page);
}
