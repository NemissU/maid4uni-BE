package com.swp391.maid4uni.controller;

import com.swp391.maid4uni.converter.TaskConverter;
import com.swp391.maid4uni.dto.TaskDto;
import com.swp391.maid4uni.enums.API_PARAMS;
import com.swp391.maid4uni.request.UpdateTaskRequest;
import com.swp391.maid4uni.response.ResponseObject;
import com.swp391.maid4uni.service.TaskService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
@CrossOrigin(origins = "*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskController {
    @Autowired
    TaskService taskService;

    @PutMapping(API_PARAMS.UPDATE_TASK)
    public ResponseEntity<ResponseObject> updateTask(
            @PathVariable int id,
            @RequestBody UpdateTaskRequest updateTaskRequest) {
        log.info("Start update task");
        updateTaskRequest.setId(id);
        TaskDto taskDto = TaskConverter
                .INSTANCE
                .fromUpdateTaskRequestToTaskDto(updateTaskRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK"
                        , "UPDATE TASK SUCCESSFULLY"
                        , taskService.updateTask(taskDto, id))
        );
    }

    @GetMapping(API_PARAMS.GET_TASK_BY_STAFF_ID)
    public ResponseEntity<ResponseObject> getTaskByStaffId(@PathVariable int id, @PathVariable int page) {
        log.info("Start get task by staff id");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK"
                , "GET TASK BY STAFF ID SUCCESSFULLY"
                , taskService.getTaskByStaffId(id, page)));
    }

}
