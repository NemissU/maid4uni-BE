package com.swp391.maid4uni.controller;

import com.swp391.maid4uni.converter.PackageConverter;
import com.swp391.maid4uni.converter.TaskConverter;
import com.swp391.maid4uni.dto.PackageDto;
import com.swp391.maid4uni.dto.TaskDto;
import com.swp391.maid4uni.enums.API_PARAMS;
import com.swp391.maid4uni.request.UpdatePackageRequest;
import com.swp391.maid4uni.request.UpdateTaskRequest;
import com.swp391.maid4uni.response.ResponseObject;
import com.swp391.maid4uni.service.TaskService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(API_PARAMS.API_VERSION)
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskController {
    @Autowired
    TaskService taskService;

    @PutMapping(API_PARAMS.UPDATE_TASK)
    public ResponseEntity<ResponseObject> updateTask(
            @PathVariable int id,
            @RequestBody UpdateTaskRequest updateTaskRequest) {
        log.info("Start update task");
        TaskDto taskDto = TaskConverter
                .INSTANCE
                .fromUpdateTaskRequestToTaskDto(updateTaskRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK"
                        ,"UPDATE TASK SUCCESSFULLY"
                        ,taskService.updateTask(taskDto, id))
        );
    }
}
