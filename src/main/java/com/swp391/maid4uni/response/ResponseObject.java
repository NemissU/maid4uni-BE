package com.swp391.maid4uni.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type Response object.
 */
@Data
@AllArgsConstructor
public class ResponseObject {
    private String status;
    private String message;
    private Object data;
}
