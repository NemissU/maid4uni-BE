package com.swp391.maid4uni.service.impl;

import com.swp391.maid4uni.converter.TaskConverter;
import com.swp391.maid4uni.dto.TaskDto;
import com.swp391.maid4uni.entity.OrderDetail;
import com.swp391.maid4uni.entity.Task;
import com.swp391.maid4uni.enums.Status;
import com.swp391.maid4uni.exception.Maid4UniException;
import com.swp391.maid4uni.repository.OrderDetailRepository;
import com.swp391.maid4uni.repository.TaskRepository;
import com.swp391.maid4uni.response.OrderResponse;
import com.swp391.maid4uni.response.TaskResponse;
import com.swp391.maid4uni.service.TaskService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class TaskServiceImp implements TaskService {
    TaskRepository taskRepository;
    OrderDetailRepository orderDetailRepository;

    @Override
    public TaskResponse updateTask(TaskDto taskDto, int id) {
        Optional<Task> task = taskRepository.findById(id);
        Task updatedTask = new Task();
        if (task.isPresent()) {
            updatedTask = task.get();
            updatedTask.setStatus(taskDto.isStatus());
            taskRepository.save(updatedTask);

            boolean flag = true;
            OrderDetail orderDetailValidate = updatedTask.getOrderDetail();
            for (Task t : orderDetailValidate.getTaskList()) {
                if(!t.isStatus()){
                    flag = false;
                    break;
                }
            }
            if (flag){
                orderDetailValidate.setStatus(Status.DONE);
                orderDetailRepository.save(orderDetailValidate);
            }
        } else {
            throw Maid4UniException.notFound("Task " + id + " does not exist");
        }
        return TaskConverter.INSTANCE.fromTaskToTaskResponse(updatedTask);
    }

    @Override
    public List<TaskResponse> getTaskByStaffId(int id, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        List<Task> taskList = taskRepository.findAllByStaffIdWithOffSetAndLimit(id, pageable);
        List<TaskResponse> taskResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(taskList)) {
            taskResponseList = taskList.stream()
                    .map(TaskConverter.INSTANCE::fromTaskToTaskResponse)
                    .toList();
        }
        return taskResponseList;
    }
}
