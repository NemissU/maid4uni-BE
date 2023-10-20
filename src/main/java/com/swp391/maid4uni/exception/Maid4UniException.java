package com.swp391.maid4uni.exception;

import com.swp391.maid4uni.model.CustomError;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

/**
 * The type Maid 4 uni exception.
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Maid4UniException extends RuntimeException{
    HttpStatus status;
    CustomError error;

    /**
     * Not found maid 4 uni exception.
     *
     * @param message the message
     * @return the maid 4 uni exception
     */
    public static Maid4UniException notFound(String message) {
        return Maid4UniException.builder()
                .status(HttpStatus.NOT_FOUND)
                .error(CustomError.builder()
                        .code("404")
                        .message(message)
                        .build())
                .build();
    }

    /**
     * Bad request maid 4 uni exception.
     *
     * @param message the message
     * @return the maid 4 uni exception
     */
    public static Maid4UniException badRequest(String message) {
        return Maid4UniException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error(CustomError.builder()
                        .code("400")
                        .message(message)
                        .build())
                .build();
    }
}
