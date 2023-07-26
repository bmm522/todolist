package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFailedException extends Exception{

    String message;

    public LoginFailedException(String message) {
        this.message = message;
        log.error(message);
    }
}
