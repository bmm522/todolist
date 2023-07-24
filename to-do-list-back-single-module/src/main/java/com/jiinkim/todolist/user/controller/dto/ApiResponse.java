package com.jiinkim.todolist.user.controller.dto;


import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final Integer status;
    private final T body;

    private ApiResponse(Integer status, T data) {
        this.status = status;
        this.body = data;
    }

    // 정상 요청
    public static <T> ApiResponse<T> success(final Integer code, final T data) {
        return new ApiResponse<>(code, data);
    }

    // 비 정상 요청
    public static <T> ApiResponse<T> fail(final Integer code,  final T error) {
        return new ApiResponse<>(code,  error);
    }


}
