package com.jiinkim.todolist.user.controller.dto;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    private final Integer code;
    private final T body;

    private ApiResponse(HttpStatus status,final T data) {
        this.code = status.value();
        this.body = data;
    }

    // 정상 요청
    public static <T> ApiResponse<T> success(final HttpStatus status, final T data) {
        return new ApiResponse<>(status, data);
    }

    // 비 정상 요청
    public static <T> ApiResponse<T> fail(final HttpStatus status,  final T error) {
        return new ApiResponse<>(status,  error);
    }


}
