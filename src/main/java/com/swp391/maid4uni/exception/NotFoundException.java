package com.swp391.maid4uni.exception;

import com.swp391.maid4uni.model.CustomError;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException{
    CustomError customError;

    public NotFoundException(CustomError customError) {
        this.customError = customError;
    }
}
