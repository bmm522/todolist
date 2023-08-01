package com.jiinkim.todolist.common.exception.unauthorized;

import com.jiinkim.todolist.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundTokenFromHeaderException  extends UnAuthorizedException {


    public NotFoundTokenFromHeaderException(final String message) {
        super(message);
    }
}
