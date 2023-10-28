package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.TaskDto;
import com.swp391.maid4uni.entity.Task;
import com.swp391.maid4uni.request.UpdateTaskRequest;
import com.swp391.maid4uni.response.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskConverter {
    TaskConverter INSTANCE = Mappers.getMapper(TaskConverter.class);

    TaskDto fromUpdateTaskRequestToTaskDto(UpdateTaskRequest updateTaskRequest);

    Task fromTaskDtoToTask(TaskDto taskDto);

    TaskResponse fromTaskToTaskResponse(Task updatedTask);
}
