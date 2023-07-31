package com.jiinkim.todolist.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationException extends CustomException{


    public AuthenticationException(final String message) {
        super(message);
    }
}
