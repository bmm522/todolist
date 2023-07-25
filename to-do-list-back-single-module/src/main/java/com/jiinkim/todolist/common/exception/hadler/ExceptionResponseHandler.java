package com.jiinkim.todolist.common.exception.hadler;

import com.jiinkim.todolist.common.exception.AuthenticationException;
import com.jiinkim.todolist.common.exception.InsertFailedException;
import com.jiinkim.todolist.common.exception.InvalidTokenException;
import com.jiinkim.todolist.common.exception.NotFoundEntityException;
import com.jiinkim.todolist.user.controller.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResponseHandler {

    @ExceptionHandler(InsertFailedException.class)
    public ApiResponse<Exception> handleInsertFailed(InsertFailedException e) {
        return ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ApiResponse<Exception> handleNotFoundEntity(NotFoundEntityException e) {
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
}
