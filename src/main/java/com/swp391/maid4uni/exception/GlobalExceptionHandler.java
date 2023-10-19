package com.swp391.maid4uni.exception;

import com.swp391.maid4uni.model.CustomError;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //
    @ExceptionHandler(Exception.class)
    public CustomError internalServer(Exception ex){
        return CustomError.builder()
                .code("500")
                .message("Internal Server")
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    public CustomError notFoundException(NotFoundException ex){
        return ex.getCustomError();
    }
}
