package com.jiinkim.todolist.common.exception.unauthorized;

import com.jiinkim.todolist.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFailedException extends UnAuthorizedException {


    public LoginFailedException(final String message) {
        super(message);
    }
}
