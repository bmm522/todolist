package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFailedException extends CustomException {


    public LoginFailedException(final String message) {
        super(message);
    }
}
