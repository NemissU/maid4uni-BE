package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.TaskConverter;
import com.swp391.maid4uni.dto.TaskDto;
import com.swp391.maid4uni.entity.Task;
import com.swp391.maid4uni.exception.Maid4UniException;
import com.swp391.maid4uni.repository.TaskRepository;
import com.swp391.maid4uni.response.TaskResponse;
import com.swp391.maid4uni.service.TaskService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class TaskServiceImp implements TaskService {
    TaskRepository taskRepository;

    @Override
    public TaskResponse updateTask(TaskDto taskDto, int id) {
        Optional<Task> task = taskRepository.findById(id);
        Task updatedTask = new Task();
        if (task.isPresent()) {
            updatedTask.setId(id);
            updatedTask = TaskConverter.INSTANCE.fromTaskDtoToTask(taskDto);
            taskRepository.save(updatedTask);
        } else {
            throw Maid4UniException.notFound("Task " + id + " does not exist");
        }
        return TaskConverter.INSTANCE.fromTaskToTaskResponse(updatedTask);
    }
}
