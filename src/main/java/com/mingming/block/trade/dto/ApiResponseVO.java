package com.mingming.block.trade.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ApiResponseVO<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> ApiResponseVO<T> success(T data) {
        ApiResponseVO<T> responseDto = new ApiResponseVO<>();
        responseDto.code = 0;
        responseDto.msg = "";
        responseDto.data = data;
        return responseDto;
    }

    public static ApiResponseVO fail(Exception e) {
        ApiResponseVO responseDto = new ApiResponseVO();
        responseDto.code = 1;
        responseDto.msg = e.getMessage();
        return responseDto;
    }

    @JsonIgnore
    public boolean isFailed() {
        return code == 1;
    }
}
