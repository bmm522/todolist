package com.jiinkim.todolist.common.exception.hadler;

import com.jiinkim.todolist.common.exception.*;
import com.jiinkim.todolist.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResponseHandler {

    @ExceptionHandler({InsertFailedException.class, UpdateFailedException.class, NotFoundQueryResultException.class})
    public ApiResponse<Exception> handleInsertFailed(CustomException e) {
        return ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({AuthenticationException.class, InvalidTokenException.class, NotFoundTokenFromHeaderException.class})
    public ApiResponse<Exception> handleAuthentication(CustomException e) {
        return ApiResponse.fail(HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler(PermissionException.class)
    public ApiResponse<Exception> handlePermission(PermissionException e) {
        return ApiResponse.fail(HttpStatus.FORBIDDEN, e);
    }

    @ExceptionHandler(DeleteTodoIdListEmptyException.class)
    public ApiResponse<CustomException> handleDeleteTodoIdListEmpty(DeleteTodoIdListEmptyException e) {
        return ApiResponse.fail(HttpStatus.BAD_REQUEST, e);
    }


}
