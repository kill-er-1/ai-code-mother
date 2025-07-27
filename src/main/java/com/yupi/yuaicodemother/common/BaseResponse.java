package com.yupi.yuaicodemother.common;

import com.yupi.yuaicodemother.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author cin
 * @version 1.0
 * @date 2025/7/27 07:28
 * @className BaseResponse
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}

