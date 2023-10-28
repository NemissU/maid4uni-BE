package com.swp391.maid4uni.service;

import com.swp391.maid4uni.dto.TaskDto;
import com.swp391.maid4uni.response.TaskResponse;

public interface TaskService {
    TaskResponse updateTask(TaskDto taskDto, int id);
}
