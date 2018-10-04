package com.mingming.block.trade.dto;

import lombok.Data;

@Data
public class ApiResponseDto<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> ApiResponseDto<T> success(T data) {
        ApiResponseDto<T> responseDto = new ApiResponseDto<>();
        responseDto.code = 0;
        responseDto.msg = "";
        responseDto.data = data;
        return responseDto;
    }

    public static ApiResponseDto fail(Exception e) {
        ApiResponseDto responseDto = new ApiResponseDto();
        responseDto.code = 1;
        responseDto.msg = e.getMessage();
        return responseDto;
    }
}
