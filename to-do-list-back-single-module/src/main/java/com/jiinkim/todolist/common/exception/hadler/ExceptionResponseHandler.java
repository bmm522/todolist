package com.jiinkim.todolist.common.exception.hadler;

import com.jiinkim.todolist.common.exception.*;
import com.jiinkim.todolist.common.dto.ApiResponse;
import com.jiinkim.todolist.common.exception.badrequest.BadRequestException;
import com.jiinkim.todolist.common.exception.badrequest.DeleteTodoIdListEmptyException;
import com.jiinkim.todolist.common.exception.forbidden.ForbiddenException;
import com.jiinkim.todolist.common.exception.forbidden.PermissionException;
import com.jiinkim.todolist.common.exception.servererror.InternalServerException;
import com.jiinkim.todolist.common.exception.unauthorized.AuthenticationException;
import com.jiinkim.todolist.common.exception.unauthorized.InvalidTokenException;
import com.jiinkim.todolist.common.exception.unauthorized.NotFoundTokenFromHeaderException;
import com.jiinkim.todolist.common.exception.unauthorized.UnAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResponseHandler {

        @ExceptionHandler(InternalServerException.class)
        public ApiResponse<InternalServerException> handleInternalServer(
            InternalServerException e) {

                return ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        @ExceptionHandler(UnAuthorizedException.class)
        public ApiResponse<UnAuthorizedException> handleUnAuthorized(UnAuthorizedException e) {

                return ApiResponse.fail(HttpStatus.UNAUTHORIZED, e);
        }

        @ExceptionHandler(ForbiddenException.class)
        public ApiResponse<ForbiddenException> handleForbidden(ForbiddenException e) {

                return ApiResponse.fail(HttpStatus.FORBIDDEN, e);
        }

        @ExceptionHandler(BadRequestException.class)
        public ApiResponse<BadRequestException> handleBadRequest(BadRequestException e) {

                return ApiResponse.fail(HttpStatus.BAD_REQUEST, e);
        }


}
