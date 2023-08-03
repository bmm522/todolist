package com.jiinkim.todolist.common.dto;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse<T> {

    // ResponseEntity<ApiResponse<T>>
    /*
     *
     * */
    private final Integer code;
    // status code
    // resultCode 2-- ~ 500

    private final T body;

    private ApiResponse(final HttpStatus status, final T data) {
        ResponseEntity<?> entity = ResponseEntity.status(200)
            .header("acce")
            .body(null);
        this.code = status.value();
        this.body = data;
    }

    private ApiResponse(final int code) {

        this.code = code;
        this.body = null;
    }

    // 정상 요청
    public static <T> ApiResponse<T> success(final HttpStatus status, final T data) {

        return new ApiResponse<>(status, data);
    }

    // 비 정상 요청
    public static <T> ApiResponse<T> fail(final HttpStatus status, final T error) {

        return new ApiResponse<>(status, error);
    }

    // filter 단 에러 처리
    public static <T> ApiResponse<T> fail(final int code) {
        // 생성자
        return new ApiResponse<>(code);
    }
    
}
