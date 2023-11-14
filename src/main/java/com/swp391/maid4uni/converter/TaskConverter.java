package com.swp391.maid4uni.converter;

import com.swp391.maid4uni.dto.TaskDto;
import com.swp391.maid4uni.entity.OrderDetail;
import com.swp391.maid4uni.entity.Task;
import com.swp391.maid4uni.request.UpdateTaskRequest;
import com.swp391.maid4uni.response.OrderDetailResponse;
import com.swp391.maid4uni.response.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface TaskConverter {
    TaskConverter INSTANCE = Mappers.getMapper(TaskConverter.class);

    TaskDto fromUpdateTaskRequestToTaskDto(UpdateTaskRequest updateTaskRequest);

    Task fromTaskDtoToTask(TaskDto taskDto);

    OrderDetailResponse map(OrderDetail orderDetail);

    TaskResponse fromTaskToTaskResponse(Task updatedTask);

    default String convertToString(ArrayList<Integer> inputList) {
        StringBuilder sb = new StringBuilder();
        for (Integer element : inputList) {
            sb.append(element).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);  // Xóa dấu ',' cuối cùng
        }
        return sb.toString();
    }
}
