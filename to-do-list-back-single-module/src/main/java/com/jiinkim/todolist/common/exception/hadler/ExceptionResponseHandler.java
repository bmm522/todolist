package com.jiinkim.todolist.common.exception.hadler;

import com.jiinkim.todolist.common.exception.*;
import com.jiinkim.todolist.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResponseHandler {

    @ExceptionHandler(InsertFailedException.class)
    public ApiResponse<Exception> handleInsertFailed(InsertFailedException e) {
        return ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(NotFoundQueryResultException.class)
    public ApiResponse<Exception> handleNotFoundEntity(NotFoundQueryResultException e) {
        return ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ApiResponse<Exception> handleAuthentication(AuthenticationException e) {
        return ApiResponse.fail(HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ApiResponse<Exception> handleInvalidToken(InvalidTokenException e) {
        return ApiResponse.fail(HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler(NotFoundTokenFromHeaderException.class)
    public ApiResponse<Exception> handleNotFoundTokenFromHeader(NotFoundTokenFromHeaderException e) {
        return ApiResponse.fail(HttpStatus.UNAUTHORIZED, e);
    }
}
