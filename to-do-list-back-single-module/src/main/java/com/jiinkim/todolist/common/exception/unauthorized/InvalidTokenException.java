package com.jiinkim.todolist.common.exception.unauthorized;

import com.jiinkim.todolist.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidTokenException  extends UnAuthorizedException {

    public InvalidTokenException(final String message) {
        super(message);
    }
}
