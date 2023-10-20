package com.swp391.maid4uni.exception;

import com.swp391.maid4uni.model.CustomError;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The type Global exception handler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handle online shop exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(Maid4UniException.class)
    public ResponseEntity<CustomError> handleOnlineShopException(Maid4UniException ex){
        return new ResponseEntity<>(ex.getError(), ex.getStatus());
    }
}
